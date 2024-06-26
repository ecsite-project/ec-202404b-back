package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.example.domain.Item;
import com.example.domain.Option;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;

import jakarta.mail.internet.MimeMessage;
import lombok.val;


public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);


    public void sendHtmlMessage(Order order, User user) {
        try {
            val javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost("smtp.gmail.com");
            javaMailSender.setPort(587);
            javaMailSender.setUsername("quymao08101999@gmail.com");
            javaMailSender.setPassword("tfpyllxjmdxynlhp");
            javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
            javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
            
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(order.getDestinationEmail());
            helper.setSubject("ご注文ありがとうございます！");

            // HTMLメールの内容を構築
            String content = buildHtmlContent(order, user);
            helper.setText(content, true);

            javaMailSender.send(message);

            log.info(order.getId() + "のオーダーのHTMLメールを送信しました。");
        } catch (Exception e) {
            log.error("HTMLメールの送信中にエラーが発生しました: {}", e.getMessage());
        }
    }

    private String buildHtmlContent(Order order, User user) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n")
                .append("<html lang=\"ja\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("    <title>注文完了</title>\n")
                .append("    <style>\n")
                .append("        body {\n")
                .append("            font-family: Arial, sans-serif;\n")
                .append("            background-color: #f6f6f6;\n")
                .append("            margin: 0;\n")
                .append("            padding: 0;\n")
                .append("        }\n")
                .append("        .container {\n")
                .append("            width: 100%;\n")
                .append("            max-width: 600px;\n")
                .append("            margin: 0 auto;\n")
                .append("            background-color: #ffffff;\n")
                .append("            padding: 20px;\n")
                .append("            border: 1px solid #ddd;\n")
                .append("        }\n")
                .append("        .header {\n")
                .append("            text-align: center;\n")
                .append("            background-color: #4CAF50;\n")
                .append("            color: white;\n")
                .append("            padding: 10px 0;\n")
                .append("        }\n")
                .append("        .content {\n")
                .append("            margin: 20px 0;\n")
                .append("        }\n")
                .append("        .order-details, .customer-details {\n")
                .append("            width: 100%;\n")
                .append("            border-collapse: collapse;\n")
                .append("            margin-bottom: 20px;\n")
                .append("        }\n")
                .append("        .order-details th, .order-details td, .customer-details th, .customer-details td {\n")
                .append("            border: 1px solid #ddd;\n")
                .append("            padding: 8px;\n")
                .append("            text-align: left;\n")
                .append("        }\n")
                .append("        .order-details th, .customer-details th {\n")
                .append("            background-color: #f2f2f2;\n")
                .append("        }\n")
                .append("        .footer {\n")
                .append("            text-align: center;\n")
                .append("            font-size: 12px;\n")
                .append("            color: #888;\n")
                .append("            margin-top: 20px;\n")
                .append("        }\n")
                .append("    </style>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("    <div class=\"container\">\n")
                .append("        <div class=\"header\">\n")
                .append("            <h1>ご注文ありがとうございます！</h1>\n")
                .append("        </div>\n")
                .append("        <div class=\"content\">\n")
                .append("            <p>こんにちは ").append(user.getFirstName()).append(" ").append(user.getLastName()).append(" 様、</p>\n")
                .append("            <p>このたびはご注文いただき、誠にありがとうございます。以下はご注文内容の詳細です。</p>\n")
                .append("            <h2>注文詳細</h2>\n")
                .append("            <table class=\"order-details\">\n")
                .append("                <tr>\n")
                .append("                    <th>商品名</th>\n")
                .append("                    <th>数量</th>\n")
                .append("                    <th>価格</th>\n")
                .append("                </tr>\n");

        for (OrderItem orderItem : order.getOrderItems()) {
            Item item = orderItem.getItem();
            html.append("                <tr>\n")
                    .append("                    <td>").append(item.getBreed().getName()).append("</td>\n")
                    .append("                    <td>1</td>\n")
                    .append("                    <td>").append(item.getPrice()).append("</td>\n")
                    .append("                </tr>\n");
            System.out.println(orderItem.getOptions().size());
            for (Option option : orderItem.getOptions()) {
                html.append("                <tr>\n")
                        .append("                    <td>").append(option.getName()).append("</td>\n")
                        .append("                    <td>1</td>\n")
                        .append("                    <td>").append(option.getPrice()).append("</td>\n")
                        .append("                </tr>\n");
            }
        }

        html.append("                <tr>\n")
                .append("                    <th colspan=\"2\">合計</th>\n")
                .append("                    <th>").append(order.getTotalPrice()).append("</th>\n")
                .append("                </tr>\n")
                .append("            </table>\n")
                .append("            <h2>顧客情報</h2>\n")
                .append("            <table class=\"customer-details\">\n")
                .append("                <tr>\n")
                .append("                    <th>名前</th>\n")
                .append("                    <td>").append(order.getDestinationName()).append("</td>\n")
                .append("                </tr>\n")
                .append("                <tr>\n")
                .append("                    <th>メールアドレス</th>\n")
                .append("                    <td>").append(order.getDestinationEmail()).append("</td>\n")
                .append("                </tr>\n")
                .append("                <tr>\n")
                .append("                    <th>配送先住所</th>\n")
                .append("                    <td>").append(order.getDestinationAddress()).append("</td>\n")
                .append("                </tr>\n")
                .append("                <tr>\n")
                .append("                    <th>配達日時</th>\n")
                .append("                    <td>").append(order.getDeliveryDate()).append(" ").append(order.getTimeRange().getDisplayName()).append("</td>\n")
                .append("                </tr>\n")
                .append("                <tr>\n")
                .append("                    <th>支払い方法</th>\n")
                .append("                    <td>").append(order.getPaymentMethod()).append("</td>\n")
                .append("                </tr>\n")
                .append("            </table>\n")
                .append("            <p>今後ともよろしくお願いいたします。</p>\n")
                .append("        </div>\n")
                .append("        <div class=\"footer\">\n")
                .append("            <p>&copy; 2024 ").append("楽ペットショップ").append(". All rights reserved.</p>\n")
                .append("            <p>住所: ").append("東京都新宿区〇〇").append(" | 電話: ").append("132-456-789").append("</p>\n")
                .append("        </div>\n")
                .append("    </div>\n")
                .append("</body>\n")
                .append("</html>\n");


        return html.toString();
    }
}
