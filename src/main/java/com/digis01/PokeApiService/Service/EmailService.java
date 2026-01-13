package com.digis01.PokeApiService.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String link, String subjectEmail) throws MessagingException{
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
        
        helper.setTo(to);
        
        String html = "";
        
        helper.setSubject(subjectEmail);
        
        html = """
            <html>
                <body style="margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f4f9;">
                    
                    <table role="presentation" width="100%%" cellspacing="0" cellpadding="0" border="0" style="background-color: #f4f4f9; padding: 40px 0;">
                        <tr>
                            <td align="center">
                                
                                <table role="presentation" width="600" cellspacing="0" cellpadding="0" border="0" style="background-color: #ffffff; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 90%%;">
                                    
                                    <tr>
                                        <td style="background-color: #ff3e3e; padding: 30px; text-align: center;">
                                            <h1 style="color: #ffffff; margin: 0; font-size: 24px; text-transform: uppercase; letter-spacing: 3px; font-weight: 800;">
                                                <span style="font-size: 28px; vertical-align: middle;">&#9679;</span> POKÉDEX
                                            </h1>
                                        </td>
                                    </tr>
                
                                    <tr>
                                        <td style="padding: 40px 30px; text-align: center;">
                                            
                                            <img src="https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/058.png" alt="Pokéball" width="150" style="margin-bottom: 20px; opacity: 0.8;">
                
                                            <h2 style="color: #333333; font-size: 22px; margin-bottom: 10px; font-weight: 700;">¿Olvidaste tu contraseña?</h2>
                                            
                                            <p style="color: #666666; font-size: 16px; line-height: 1.6; margin-bottom: 30px;">
                                                Hola.<br>
                                                Hemos recibido una solicitud para restablecer la contraseña de tu cuenta en la Pokédex.
                                            </p>
                
                                            <table role="presentation" cellspacing="0" cellpadding="0" border="0" style="margin: auto;">
                                                <tr>
                                                    <td style="border-radius: 50px; background-color: #ff3e3e;">
                                                        <a href="%s" target="_blank" style="font-size: 16px; font-family: sans-serif; font-weight: bold; color: #ffffff; text-decoration: none; padding: 14px 40px; border-radius: 50px; border: 1px solid #ff3e3e; display: inline-block; box-shadow: 0 4px 6px rgba(255, 62, 62, 0.3);">
                                                            Restablecer Contraseña
                                                        </a>
                                                    </td>
                                                </tr>
                                            </table>
                
                                            <p style="color: #999999; font-size: 13px; margin-top: 30px; margin-bottom: 0;">
                                                Si no solicitaste este cambio, puedes ignorar este correo.
                                            </p>
                                        </td>
                                    </tr>
                
                                    <tr>
                                        <td style="background-color: #2c3e50; padding: 20px; text-align: center;">
                                            <p style="color: #bdc3c7; font-size: 12px; margin: 0;">
                                                &copy; 2026.<br>
                                            </p>
                                        </td>
                                    </tr>
                
                                </table>
                            </td>
                        </tr>
                    </table>
                
                </body>
           </html>
        """.formatted(link, link);
        
        helper.setText(html, true);
        javaMailSender.send(message);
    }
    
    public void sendNotification(String to, String subject) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        
        String html = "";

        html = """
            <html lang="es">
            <body style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f4f7; color: #2c3e50; margin: 0; padding: 0; width: 100%% !important;">
            
                <table role="presentation" width="100%%" cellspacing="0" cellpadding="0" border="0" style="background-color: #f4f4f7; padding: 40px 0;">
                    <tr>
                        <td align="center">
                            
                            <table role="presentation" width="600" cellspacing="0" cellpadding="0" border="0" style="background-color: #ffffff; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 20px rgba(0,0,0,0.05); max-width: 90%%;">
                                
                                <tr>
                                    <td style="background-color: #ff3e3e; padding: 25px; text-align: center;">
                                        <h1 style="color: #ffffff; margin: 0; font-size: 20px; text-transform: uppercase; letter-spacing: 2px; font-weight: 800;">
                                            <span style="font-size: 24px; vertical-align: middle;">&#9679;</span> Seguridad
                                        </h1>
                                    </td>
                                </tr>
                        
                                <tr>
                                    <td style="padding: 40px 30px;">
                                        
                                        <div style="text-align: center; margin-bottom: 20px;">
                                            <img src="https://cdn-icons-png.flaticon.com/512/148/148767.png" alt="Check" width="50" style="opacity: 0.8;">
                                        </div>
            
                                        <h2 style="color: #2d3748; text-align: center; margin-top: 0; font-size: 22px;">Contraseña Actualizada</h2>
                                        
                                        <p style="font-size: 16px; line-height: 1.6; color: #51545e; text-align: center;">
                                            Hola.<br>
                                            Te informamos que la contraseña de tu cuenta Pokedex ha sido modificada exitosamente el día de hoy.
                                        </p>
                                        
                                        <hr style="border: 0; border-top: 1px solid #eee; margin: 30px 0;">
            
                                        
            
                                        <p style="text-align: center; margin-top: 30px; font-size: 14px; color: #718096;">
                                            Si fuiste tú, puedes ignorar este mensaje.
                                        </p>
                                    </td>
                                </tr>
                        
                                <tr>
                                    <td style="background-color: #2c3e50; padding: 20px; text-align: center;">
                                        <p style="color: #a0aec0; font-size: 12px; margin: 0;">
                                            &copy; 2026.<br>
                                        </p>
                                    </td>
                                </tr>
            
                            </table>
                        </td>
                    </tr>
                </table>
            
            </body>
            </html>
        """;
        
        helper.setText(html, true);
        javaMailSender.send(message);
    }
}
