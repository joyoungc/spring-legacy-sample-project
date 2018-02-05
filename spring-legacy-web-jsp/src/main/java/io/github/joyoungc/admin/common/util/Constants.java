package io.github.joyoungc.admin.common.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class Constants {

	public static final int MAX_REQUEST_REPEAT_COUNT = 5;
	public static final Map<String, String> WORKSPACE_ID_MAP;

	public static final String YES = "Y";
	public static final String NO = "N";

	static {
		
		Map<String, Object> orgMap = null;
		Map<String, String> resultMap = new HashMap<>();

		try {

			orgMap = new ObjectMapper().readValue(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("config/data/config.json"),
					HashMap.class);
			List<Map<String, String>> list = (List<Map<String, String>>) orgMap.get("configs");
			list.forEach(e -> resultMap.put(e.get("name"), e.get("config_id")));
			// System.out.println(resultMap);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		WORKSPACE_ID_MAP = Collections.unmodifiableMap(resultMap);

	}

}
