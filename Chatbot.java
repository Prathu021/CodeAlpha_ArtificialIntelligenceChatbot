import java.util.HashMap;
import java.util.Map;

public class Chatbot {
    private Map<String, String> knowledgeBase;

    public Chatbot() {
        knowledgeBase = new HashMap<>();

        // Sample FAQs
        knowledgeBase.put("hi", "Hello! How can I help you today?");
        knowledgeBase.put("hello", "Hi there!");
        knowledgeBase.put("how are you", "I'm just a bot, but I'm doing great!");
        knowledgeBase.put("what is your name", "I am JavaBot.");
        knowledgeBase.put("bye", "Goodbye! Have a great day.");
        knowledgeBase.put("help", "I can help you with general questions. Try saying 'hi', 'what is your name', etc.");
    }

    public String getResponse(String input) {
        input = input.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");

        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "I'm sorry, I don't understand. Can you rephrase?";
    }
}
