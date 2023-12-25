package org.ember.emberbackend.utils.generators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class IdGenerator {
    private static int TOTAL_ID_COUNT = 0;
    private static Random random = new Random();
    private static final HashMap<Integer, String> generatedIds = new HashMap<>();
    private static final String alphanumeric = "A0B1C2D3E4F5G6H7I8J9K1L2M3N4O5P6Q7R8S9T0U1V2W3X4Y5Z6";


    // TODO: USE THIS IMPLEMENTATION (SOMEWHERE)
    public static String generateBase(int count, String base) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(base.charAt(random.nextInt(base.length() - 1)));
        }

        return sb.toString();

    }

    public static String generateChatId(String senderId, String recipientId) {
        return String.format("RMCI:%s-%s", senderId, recipientId);

    }
}
