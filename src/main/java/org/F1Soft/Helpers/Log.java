package org.F1Soft.Helpers;

/**
 * Created by Subash Paudel.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
public class Log {



        static Logger logger = LoggerFactory.getLogger(Log.class);



        // This is to print logger for the beginning of the test case, as we usually run so many test cases as a test suite

        public static void startTestCase(String TestCaseName){

            logger.info("****************************************************************************************");

            logger.info("****************************************************************************************");

            logger.info("$$$$$$$$$$$$$$$$$$$$$                 "+TestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

            logger.info("****************************************************************************************");

            logger.info("****************************************************************************************");

        }

        public static void startTestCase(String TestCaseName,Object obj){
            startTestCase(TestCaseName);
            logger.info("############### Test Data ###########################");
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true); // You might want to set modifier to public first.
                Object value = null;
                try {
                    value = field.get(obj);
                } catch (IllegalAccessException e) {
                    logger.warn(e.getMessage());
                }
                if (value != null) {
                    logger.info(field.getName() + ":" + value);
                }
            }
            logger.info("######################################################");
        }

        //This is to print logger for the ending of the test case

        public static void endTestCase(String sTestCaseName){

            logger.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

            logger.info("X");

            logger.info("X");

            logger.info("X");

            logger.info("X");

        }

        // Need to create these methods, so that they can be called

        public static void info(String message) {

            logger.info(message);

        }

        public static void warn(String message) {

            logger.warn(message);

        }

        public static void error(String message) {

            logger.error(message);

        }

        public static void debug(String message) {

            logger.debug(message);

        }

        public static void typing(String elementName, String value){
        logger.info("Typing value:'"+value+"' into Web Elemnet:'"+elementName+"'.");
        }

    }

