/**
 * 
 */
package com.li.dubbo.lido.remote;

/**
 * @author lipeng
 *
 * 2016年5月13日
 */
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 14-6-24
 * Time: 下午3:34
 * To change this template use File | Settings | File Templates.
 */
public class TargetHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(">>>>>>>>ACTIVE>>>>>>>>");
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("proxy said:" + msg.toString());
        // 向代理服务器发送消息
        System.out.println("After the target server to the proxy server receives a message to the proxy server, said: I am the target server, I thank you on behalf of the client.");
        String response = "I was the target server, I thank you for the client.";
        ctx.write(response);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(">>>>>>>>IN-ACTIVE>>>>>>>>");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        closeOnFlush(ctx.channel());
    }

    /**
     * Closes the specified channel after all queued write requests are flushed.
     */
    static void closeOnFlush(Channel ch) {
        if (ch.isActive()) {
            ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }
}