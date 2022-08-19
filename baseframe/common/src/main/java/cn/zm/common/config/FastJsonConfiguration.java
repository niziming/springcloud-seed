package cn.zm.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FastJsonConfiguration {
    @Bean
    public HttpMessageConverter configureMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(

                // // 保留map空的字段
                // SerializerFeature.WriteMapNullValue,
                // // 将String类型的null转成""
                // SerializerFeature.WriteNullStringAsEmpty,
                // // 将Number类型的null转成0
                // SerializerFeature.WriteNullNumberAsZero,
                // // 将List类型的null转成[]
                // SerializerFeature.WriteNullListAsEmpty,
                // // 将Boolean类型的null转成false
                // SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);
        // 时间格式
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 解决中文乱码问题,相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        return converter;
    }
}

// @Configuration
// public class WebConfig implements WebMvcConfigurer {
//
//     public HttpMessageConverter<String> stringConverter() {
//         return new StringHttpMessageConverter(StandardCharsets.UTF_8);
//     }
//
//     public FastJsonHttpMessageConverter fastConverter() {
//         //1、定义一个convert转换消息的对象
//         FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//         //2、添加fastJson的配置信息
//         FastJsonConfig fastJsonConfig = new FastJsonConfig();
//         fastJsonConfig.setSerializerFeatures(
//                 SerializerFeature.WriteMapNullValue,
//                 SerializerFeature.WriteNullStringAsEmpty,
//                 SerializerFeature.WriteNullNumberAsZero,
//                 SerializerFeature.WriteNullListAsEmpty,
//                 SerializerFeature.WriteNullBooleanAsFalse);
//
//         fastJsonConfig.setCharset(StandardCharsets.UTF_8);
//         //2-1 处理中文乱码问题
//         List<MediaType> fastMediaTypes = new ArrayList<>();
//         fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//         fastConverter.setSupportedMediaTypes(fastMediaTypes);
//         //3、在convert中添加配置信息
//         fastConverter.setFastJsonConfig(fastJsonConfig);
//         return fastConverter;
//     }
//
//     @Override
//     public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//         converters.clear();
//         converters.add(stringConverter());
//         converters.add(fastConverter());
//     }
// }
