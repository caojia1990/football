package com.eastng.football.web.controller.util;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * 
 * @author laughing
 * @from http://blog.csdn.net/zhanngle/article/details/24123659/#
 * 
 */
@Component
public class JsonDateUtil {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final ObjectMapper mapper;

	public ObjectMapper getMapper() {
		return mapper;
	}

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

		mapper = new ObjectMapper();
		mapper.setDateFormat(dateFormat);
		mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public Object findSerializer(Annotated a) {
				if (a instanceof AnnotatedMethod) {
					AnnotatedElement m = a.getAnnotated();
					DateTimeFormat an = m.getAnnotation(DateTimeFormat.class);
					if (an != null) {
						if (!DEFAULT_DATE_FORMAT.equals(an.pattern())) {
							return new JsonDateSerializer(an.pattern());
						}
					}
				}
				return super.findSerializer(a);
			}
		});
	}

	public static String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException("转换json字符失败!");
		}
	}

	public <T> T toObject(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException("将json字符转换为对象时失败!");
		}
	}

	public static class JsonDateSerializer extends JsonSerializer<Date> {
		private SimpleDateFormat dateFormat;

		public JsonDateSerializer(String format) {
			dateFormat = new SimpleDateFormat(format);
		}

		@Override
		public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			String value = dateFormat.format(date);
			gen.writeString(value);
		}
	}
}
