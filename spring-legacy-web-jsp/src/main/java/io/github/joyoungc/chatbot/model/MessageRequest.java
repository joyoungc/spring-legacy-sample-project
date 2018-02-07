package io.github.joyoungc.chatbot.model;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MessageRequest {
	
	private MessageInput input;
	private Context context;
	
	@Data
	public static class MessageInput {
		
		@Size(min = 1, max = 2048)
		private String text;
		
		public MessageInput() {}

		public MessageInput(String text) {
			this.text = text;
		}
	}

}
