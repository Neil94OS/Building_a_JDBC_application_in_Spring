import ie.neil.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@ActiveProfiles("test")
public class LanguageTests {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testFrench(){
        String message = applicationContext.getMessage("intro", null, Locale.FRENCH);
        Assertions.assertEquals("Bienvenue dans la première mission de Neils pour App. Cadres de développement SOFT8020_23613. ", message);
    }

}
