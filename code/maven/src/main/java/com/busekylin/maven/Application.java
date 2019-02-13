package com.busekylin.maven;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class Application extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer().requestHandler(req -> req.response()
                .putHeader("content-type", "text/html; charset=UTF-8")
                .end("Vert.x启动成功!")).listen(8080, http -> {
            if (http.succeeded()) {
                startFuture.complete();
                System.out.println("HTTP server started on http://localhost:8080");
            } else {
                startFuture.fail(http.cause());
            }
        });
    }
}
