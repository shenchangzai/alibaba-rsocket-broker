package com.alibaba.rsocket.invocation;

import com.alibaba.rsocket.metadata.RSocketMimeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.net.URI;
import java.nio.ByteBuffer;

/**
 * Reactive method metadata test
 *
 * @author leijuan
 */
public class ReactiveMethodMetadataTest {

    @Test
    public void testByteBufferReturn() throws Exception {
        Method rawContentMethod = this.getClass().getMethod("findById", Integer.class);
        ReactiveMethodMetadata methodMetadata = new ReactiveMethodMetadata(null, "com.alibaba.user.UserService", "",
                rawContentMethod, RSocketMimeType.Hessian, new RSocketMimeType[]{}, null, false, URI.create("tcp://127.0.0.1:0?appName=demo"));
        Assertions.assertThat(methodMetadata.getInferredClassForReturn()).isEqualTo(ByteBuffer.class);
    }

    public Mono<ByteBuffer> findById(Integer id) {
        return Mono.empty();
    }
}
