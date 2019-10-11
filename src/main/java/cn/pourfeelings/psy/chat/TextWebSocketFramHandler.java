package cn.pourfeelings.psy.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YY
 * @date 2019/4/24 -21:14
 */
public class TextWebSocketFramHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

//    public static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Map<String, Channel> userList=new HashMap<>();
    public static Map<String,String> teacherlist=new HashMap<>();
    public int initi=1;
    public String questioner=null;
    public String answer=null;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
//        System.out.println("收到消息" + msg.text());
//        ctx.channel().writeAndFlush("aaa");//处理器不支持
//        ctx.channel().writeAndFlush(new TextWebSocketFrame(ctx.channel().remoteAddress()+" :"+msg.text()));
        Channel channel = ctx.channel();
        if(initi==1){
            initi++;
            String text = msg.text();
            int i = text.lastIndexOf("+");
            answer=text.substring(i + 1);
            questioner=text.substring(0, i);
            userList.put(questioner, channel);
            return;
        }

        //老师不在线
        if(userList.get(answer)==null){
            userList.get(questioner).writeAndFlush(new TextWebSocketFrame("对方不在线，请稍后再来"));
        }else {
            //老师回答
            if(questioner.equals(answer)){
                userList.get(questioner).writeAndFlush(new TextWebSocketFrame("自己："+msg.text()));
                if(teacherlist.get(answer)!=null) {
                    userList.get(teacherlist.get(answer)).writeAndFlush(new TextWebSocketFrame(answer + "：" + msg.text()));
                }
                return;
            }
            //学生
            if(teacherlist.get(answer)==null){
                //老师不忙-忙
                teacherlist.put(answer, questioner);
            }
            if(teacherlist.get(answer).equals(questioner)){
                userList.get(answer).writeAndFlush(new TextWebSocketFrame(questioner+"："+msg.text()));
                userList.get(questioner).writeAndFlush(new TextWebSocketFrame("自己："+msg.text()));
            }else {
                userList.get(questioner).writeAndFlush(new TextWebSocketFrame("老师在忙，请稍后再来"));
            }

        }


//        channelGroup.forEach(ch ->{
//            if(channel !=ch){
//                ch.writeAndFlush(new TextWebSocketFrame(ctx.channel().remoteAddress()+" :"+msg.text()));
//            }else{
//                ch.writeAndFlush(new TextWebSocketFrame("自己 "+" :"+msg.text()));
//            }
//        });


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("handlerAdd：" + ctx.channel().id().asLongText());
//        channelGroup.writeAndFlush(new TextWebSocketFrame("[服务器] - "+channel.remoteAddress()+"- 加入"));
//        channelGroup.add(channel);


    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        userList.remove(questioner);
        //老师由忙转不忙
        if(teacherlist.get(answer).equals(questioner)){

            teacherlist.remove(answer);
        }
        System.out.println("handlerRemoved：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("有异常");
        ctx.close();
    }
}
