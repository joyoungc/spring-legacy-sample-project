package io.github.joyoungc.admin.common.util;

/**
 * 공통 상수 class
 *
 * @author joyoungc
 */
public final class Constants {

	private Constants() {};

	public static final String BASE_PACKAGE = "io.github.joyoungc";
	public static final String RESULT_MESSAGE = "resultMessage";
	public static final String YES = "Y";
	public static final String NO = "N";

	public static final int MAX_REQUEST_REPEAT_COUNT = 5;
	// public static final Map<String, String> CONFIG_MAP;

	static {

		/*
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

		CONFIG_MAP = Collections.unmodifiableMap(resultMap);
		*/

	}

}
