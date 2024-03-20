package com.kazimoto.digitalbackend.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class MaiString {

    //regular expressss
    public static final Pattern INTEGER_PATTERN = Pattern.compile("^\\d{1,100}$");
    public static final Pattern MSISDN_255 = Pattern.compile("^255[1-9]{2}[0-9]{7}");
    public static final Pattern VALID_NIN = Pattern.compile("^[\\d]{8}-[\\d]{5}-[\\d]{5}-[\\d]{2}");
    public static final Pattern VALID_TIN = Pattern.compile("^[\\d]{3}-[\\d]{3}-[\\d]{3}");

    public static final Pattern VALID_AMOUNT = Pattern.compile("^(\\d+(?:[\\.\\,]\\d{0,2})?)");
    public static final Pattern CONTAIN_ALPHABET = Pattern.compile("^[a-zA-Z]+");

    public static void main(String[] args) {
        System.out.println(isValidAmount("6534252"));
    }

    public static boolean isValidAmount(String item) {
        Matcher matcher = VALID_AMOUNT.matcher(item);
        return matcher.matches();
    }
    public static boolean isValidNin(String item) {
        Matcher matcher = VALID_NIN.matcher(item);
        return matcher.matches();
    }

    public static boolean isValidTIN(String item) {
        Matcher matcher = VALID_TIN.matcher(item);
        return matcher.matches();
    }


    public static boolean isContainsAlphabet(String item) {
        Matcher matcher = CONTAIN_ALPHABET.matcher(item);
        return matcher.matches();
    }

    //regular expressss
    public static boolean isValidUserName(String item) {
        return item.length()>3;
    }

    //regular expressss
    public static boolean isValidInteger(String item) {
        Matcher matcher = INTEGER_PATTERN.matcher(item);
        return matcher.matches();
    }



    public static boolean isValid255Msisdn(String item) {
        Matcher matcher = MSISDN_255.matcher(item);
        return matcher.matches();
    }





    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            if (param.contains("=")) {
                String name;
                String value;
                switch (param.split("=").length) {
                    case 2:
                        name = param.split("=")[0];
                        value = param.split("=")[1];
                        break;
                    case 1:
                        name = param.split("=")[0];
                        value = "-1";
                        break;
                    default:
                        name = "-1";
                        value = "-1";
                        break;
                }
                map.put(name, value);
            }
        }
        return map;
    }

    public static String randomOTP(int SECURE_TOKEN_LENGTH) {
        String CHARACTERS = "0123456789";
        SecureRandom random = new SecureRandom();
        char[] symbols = CHARACTERS.toCharArray();
        char[] buf = new char[SECURE_TOKEN_LENGTH];
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static String responseBodyBuilder(String body, String parameter1) {
        Map<String, String> map = getQueryMap(parameter1);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (key.contains("CUSTOMER_NAME")) {
                body = body.replace("{CUSTOMER_NAME}", map.get("CUSTOMER_NAME"));
            }
            if (key.contains("PARTNER_NAME")) {
                body = body.replace("{PARTNER_NAME}", map.get("PARTNER_NAME"));
            }
            if (key.contains("BILL_MESSAGE")) {
                body = body.replace("{BILL_MESSAGE}", map.get("BILL_MESSAGE"));
            }
            if (key.contains("REF_NO")) {
                body = body.replace("{REF_NO}", map.get("REF_NO"));
            }
            if (key.contains("PARTNER_ACCT_NO")) {
                body = body.replace("{PARTNER_ACCT_NO}", map.get("PARTNER_ACCT_NO"));
            }
            if (key.contains("TO_PHOME_NO")) {
                body = body.replace("{TO_PHOME_NO}", map.get("TO_PHOME_NO"));
            }
            if (key.contains("A_ACCT_NO")) {
                body = body.replace("{A_ACCT_NO}", map.get("A_ACCT_NO"));
            }
            if (key.contains("B_ACCT_NO")) {
                body = body.replace("{B_ACCT_NO}", map.get("B_ACCT_NO"));
            }
            if (key.contains("A_AMOUNT")) {
                body = body.replace("{A_AMOUNT}", map.get("A_AMOUNT"));
            }
            if (key.contains("B_AMOUNT")) {
                body = body.replace("{B_AMOUNT}", map.get("B_AMOUNT"));
            }
            if (key.contains("PAYMENT_STRUCTURE_NAME")) {
                body = body.replace("{PAYMENT_STRUCTURE_NAME}", map.get("PAYMENT_STRUCTURE_NAME"));
            }
            if (key.contains("PRINCIPAL_AMOUNT")) {
                body = body.replace("{PRINCIPAL_AMOUNT}", map.get("PRINCIPAL_AMOUNT"));
            }
            if (key.contains("PAID_AMOUNT")) {
                body = body.replace("{PAID_AMOUNT}", map.get("PAID_AMOUNT"));
            }
            if (key.contains("REMAINING_AMOUNT")) {
                body = body.replace("{REMAINING_AMOUNT}", map.get("REMAINING_AMOUNT"));
            }
            if (key.contains("START_DATE")) {
                body = body.replace("{START_DATE}", map.get("START_DATE"));
            }
            if (key.contains("END_DATE")) {
                body = body.replace("{END_DATE}", map.get("END_DATE"));
            }
            if (key.contains("V_MON_SERVICE_TYPE")) {
                body = body.replace("{V_MON_SERVICE_TYPE}", map.get("V_MON_SERVICE_TYPE"));
            }
            if (key.contains("V_HOST_NAME")) {
                body = body.replace("{V_HOST_NAME}", map.get("V_HOST_NAME"));
            }
            if (key.contains("V_URL")) {
                body = body.replace("{V_URL}", map.get("V_URL"));
            }
            if (key.contains("V_IP_ADDRESS")) {
                body = body.replace("{V_IP_ADDRESS}", map.get("V_IP_ADDRESS"));
            }
            if (key.contains("V_PORT")) {
                body = body.replace("{V_PORT}", map.get("V_PORT"));
            }
            if (key.contains("V_TIMEOUT_IN_SEC")) {
                body = body.replace("{V_TIMEOUT_IN_SEC}", map.get("V_TIMEOUT_IN_SEC"));
            }
            if (key.contains("V_RUN_EVERY_MIN")) {
                body = body.replace("{V_RUN_EVERY_MIN}", map.get("V_RUN_EVERY_MIN"));
            }
            if (key.contains("V_CONFIG_COUNT_ALERT")) {
                body = body.replace("{V_CONFIG_COUNT_ALERT}", map.get("V_CONFIG_COUNT_ALERT"));
            }
            if (key.contains("V_FAILED_COUNT_ALERT")) {
                body = body.replace("{V_FAILED_COUNT_ALERT}", map.get("V_FAILED_COUNT_ALERT"));
            }
            if (key.contains("V_LAST_STATUS")) {
                body = body.replace("{V_LAST_STATUS}", map.get("V_LAST_STATUS"));
            }
            if (key.contains("V_LAST_RUN_DATE")) {
                body = body.replace("{V_LAST_RUN_DATE}", map.get("V_LAST_RUN_DATE"));
            }
            if (key.contains("V_GRP_NAME")) {
                body = body.replace("{V_GRP_NAME}", map.get("V_GRP_NAME"));
            }
            if (key.contains("OTP")) {
                body = body.replace("{OTP}", map.get("OTP"));
            } if (key.contains("NAME")) {
                body = body.replace("{NAME}", map.get("NAME"));
            }if (key.contains("PIN")) {
                body = body.replace("{PIN}", map.get("PIN"));
            }if (key.contains("USERNAME")) {
                body = body.replace("{USERNAME}", map.get("USERNAME"));
            }if (key.contains("PASSWORD")) {
                body = body.replace("{PASSWORD}", map.get("PASSWORD"));
            }if (key.contains("APPROVAL_NAME")) {
                body = body.replace("{APPROVAL_NAME}", map.get("APPROVAL_NAME"));
            }if (key.contains("REQUESTOR_NAME")) {
                body = body.replace("{REQUESTOR_NAME}", map.get("REQUESTOR_NAME"));
            }

        }
        return body;
    }

    public static String getInputString(String varb, String parameter1, String parameter2) {
        String variable = "-1";
        Map<String, String> map = getQueryMap(parameter1 + "&" + parameter2);
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if (key.contains(varb)) {
                variable = map.get(varb);
            }
        }
        return variable;
    }


    public static String maskString(String strText, int start, int end, char maskChar)
            throws Exception {

        if (strText == null || strText.equals("")) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }

        if (end > strText.length()) {
            end = strText.length();
        }

        if (start > end) {
            throw new Exception("End index cannot be greater than start index");
        }

        int maskLength = end - start;

        if (maskLength == 0) {
            return strText;
        }

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for (int i = 0; i < maskLength; i++) {
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }

    public static List<Map<String, String>> buildListFromQuery(String parameter1) {
        Map<String, String> map = getQueryMap(parameter1);
        Set<String> keys = map.keySet();
        List<Map<String, String>> list = new ArrayList<>();
        for (String key : keys) {
            Map<String, String> row = new HashMap<>();
            row.put("item", key);
            row.put("value", map.get(key));
            list.add(row);
        }
        return list;
    }

    public static String randomString(int length) {
            String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQSTUVWXYZ";
            SecureRandom random = new SecureRandom();
            char[] symbols = CHARACTERS.toCharArray();
            char[] buf = new char[length];
            for (int idx = 0; idx < buf.length; ++idx) {
                buf[idx] = symbols[random.nextInt(symbols.length)];
            }
            return new String(buf);

    }

    public static String capitailizeWord(String str) {
        StringBuilder s = new StringBuilder();
        // Declare a character of space
        // To identify that the next character is the starting
        // of a new word
        char ch = ' ';
        for (int i = 0; i < str.length(); i++) {

            // If previous character is space and current
            // character is not space then it shows that
            // current letter is the starting of the word
            if (ch == ' ' && str.charAt(i) != ' ') {
                s.append(Character.toUpperCase(str.charAt(i)));
            } else {
                s.append(str.charAt(i));
            }
            ch = str.charAt(i);
        }

        // Return the string with trimming
        return s.toString().trim();
    }

    public static String formatMobileNo(String PhoneNo) {
        if (PhoneNo != null) {
            PhoneNo = PhoneNo.replace("+", "");
            PhoneNo = PhoneNo.replace(" ", "");
            if (PhoneNo.length() > 12) {
                PhoneNo = PhoneNo.substring(0, 12);
            }
            if (PhoneNo.length() == 9) {
                PhoneNo = "255" + PhoneNo;
            }
            if (PhoneNo.length() == 10) {
                PhoneNo = PhoneNo.replaceFirst("[0]", "255");
            }
        } else {
            PhoneNo = "NULL";
        }
        return PhoneNo;
    }

    public static String formatCurrency(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(number);
    }

    public static String formatCurrency(BigDecimal number) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(number);
    }

    public static String refFromTranDesc(String description) {
        description = description == null ? "REF:N/A DPS:" : description;
        return StringUtils.substringBetween(description, "REF:", " DPS:");
    }

    public static String gepgCtrlNoFromTranDesc(String description) {
        description = (description == null) ? "CTRL_NOT_AVAILABLE" : description;
        if (description.length() < 12) {
            description = "CTRL_NOT_AVAILABLE";
        }
        return StringUtils.substring(description, 0, 12);
    }

    public static String encloseQuoteToCsvStr(String separator, String item) {
        List<String> lists = new ArrayList<>(Arrays.asList(item.split(separator)));
        return lists.stream().map(name -> ("'" + name + "'")).collect(Collectors.joining(","));
    }

    public static List<String> conventStrToList(String separator, String item) {
        return item == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(item.split(separator)));
    }

    public static String format255Msisdn(String number) {
        number = number + "";
        number = number.replace("", "");
        number = number.replaceAll("[^\\d]", "");
        if (number != null & number.length() >= 13) {
            number = number.substring(4, 13);
        } else if (number != null & number.length() == 12) {
            number = number.substring(3, 12);
        } else if (number != null & number.length() == 10) {
            number = number.substring(1, 10);
        }
        return "255" + number;
    }

    public String removeSpecialCharachers(String rawString) {
        String plainString = rawString.replaceAll("[^a-zA-Z\\d]", "");
        return plainString;
    }

    public static String genId() {
        return UUID.randomUUID().toString().replace("-", "");
    }




    public static String sanitizeInput(String input) {
        // Use proper input validation and sanitation logic
        // For example, you can use URLEncoder to encode special characters
        return URLEncoder.encode(input, java.nio.charset.StandardCharsets.UTF_8);
    }
}
