package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlMessage(Order order) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(order.getDestinationEmail());
            helper.setSubject("ご注文ありがとうございます！");

            // HTMLメールの内容を構築
            String content = buildHtmlContent(order);
            helper.setText(content, true);

            javaMailSender.send(message);

            log.info(order.getId() + "のオーダーのHTMLメールを送信しました。");
        } catch (Exception e) {
            log.error("HTMLメールの送信中にエラーが発生しました: {}", e.getMessage());
        }
    }

    private String buildHtmlContent(Order order) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"ja\">");
        sb.append("<head><meta charset=\"UTF-8\"><title>ご注文ありがとうございます！</title></head>");
        sb.append("<body>");
        sb.append("<div>");
        sb.append("<h2>ご注文ありがとうございます！</h2>");
        sb.append("<p>この度はご注文いただき、誠にありがとうございます。</p>");
        sb.append("<div>");
        sb.append("<p><strong>注文番号:</strong> ").append(order.getId()).append("</p>");
        sb.append("<p><strong>注文日:</strong> ").append(order.getOrderDate()).append("</p>");
        sb.append("<p><strong>配送先:</strong></p>");
        sb.append("<p>").append(order.getDestinationName()).append(" 様<br>");
        sb.append(order.getDestinationZipcode()).append("<br>");
        sb.append(order.getDestinationPrefecture()).append(" ").append(order.getDestinationMunicipalities()).append("<br>");
        sb.append(order.getDestinationAddress()).append("<br>");
        sb.append("電話番号: ").append(order.getDestinationTel()).append("</p>");
        sb.append("<p><strong>配送希望日:</strong> ").append(order.getDeliveryDate()).append(" ").append(order.getTimeRange()).append("</p>");
        sb.append("<p><strong>お支払方法:</strong> ").append(order.getPaymentMethod()).append("</p>");
        sb.append("<p><strong>ご注文内容:</strong></p>");
        sb.append("<ul>");
        // for (OrderItem item : order.getOrderItems()) {
        //     sb.append("<li>")
        //       .append(item.getQuantity())
        //       .append(" x ")
        //       .append(item.getProduct().getName()) // Assuming there's a method to get the product name
        //       .append(" - ")
        //       .append(item.getPrice())
        //       .append("円</li>");
        // }
        sb.append("</ul>");
        sb.append("<p><strong>合計金額:</strong> ").append(order.getTotalPrice()).append("円 (税込)</p>");
        sb.append("</div>");
        sb.append("<p>今後ともよろしくお願い申し上げます。</p>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
