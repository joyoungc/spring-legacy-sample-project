package io.github.joyoungc.chatbot.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Context extends HashMap<String, Object> {
	
	private static final long serialVersionUID = -5222990864401336356L;
	
	@JsonProperty("conversation_id")
	private String conversationId;
	
	private Object system;
}
