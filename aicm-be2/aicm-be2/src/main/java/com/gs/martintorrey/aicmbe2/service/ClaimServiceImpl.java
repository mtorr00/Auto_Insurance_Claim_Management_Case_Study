package com.gs.martintorrey.aicmbe2.service;

import com.gs.martintorrey.aicmbe2.email.EmailService;
import com.gs.martintorrey.aicmbe2.entity.ClaimEntity;
import com.gs.martintorrey.aicmbe2.repository.ClaimRepository;
import com.gs.martintorrey.aicmbe2.repository.UserRepository;
import com.gs.martintorrey.aicmbe2.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService{
    private static final Logger LOG = LoggerFactory.getLogger(ClaimServiceImpl.class);

    @Autowired
    private ClaimRepository claimRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository) { this.claimRepository = claimRepository; }

    @Autowired
    private EmailService emailService;

    @Override
    public List<ClaimEntity> getClaims(String username, String authType) {
        if (authType.equals("admin")){
            ArrayList<ClaimEntity> reply = new ArrayList<>(claimRepository.findAll());
            LOG.info(username + " found all users using admin command");
            return reply;
        } else if (authType.equals("user")) {
            List<ClaimEntity> total = new ArrayList<>(claimRepository.findAll());
            List<ClaimEntity> reply = new ArrayList<>();
            for (ClaimEntity claimEntity : total) {
                if (username.equals(claimEntity.getFiledBy())) {
                    reply.add(claimEntity);
                }
            }
            LOG.info(username + " found all their claims using user command");
            return reply;
        } else {
            LOG.error("Error in getting claims, check getClaims() method or check user authentication.");
            throw new RuntimeException();
        }
    }

    @Override
    public ClaimEntity createClaim(String username, ClaimEntity claim, String authType) {
        if (authType.equals("user")){
            claimRepository.save(claim);
            LOG.info(username + " added new claim at id " + claim.getId());
            return claim;
        } else {
            LOG.error("Error in creating claim, check createClaims() method or check user authentication.");
            throw new RuntimeException();
        }
    }

    @Override
    public ClaimEntity saveClaim(String username, ClaimEntity claim, String authType) {
        if(authType.equals("admin")){
            claimRepository.save(claim);
            if(claim.getFiledByEmail()!=null){
                emailService.send(claim.getFiledByEmail(),emailText,"Your Claim has been Updated.");
                LOG.info("Email sent to user by " + username);
            }
            LOG.info(username + " altered the claim at id " + claim.getId());
            return claim;
        } else if (authType.equals("user")){
            claimRepository.save(claim);
            LOG.info(username + " saved changes to claim id " + claim.getId());
            return claim;
        } else {
            LOG.error("Not able to save claim, check for issues in claim rep pipeline.");
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteClaim(String username, ClaimEntity claim, String authType) {
        if (authType.equals("admin")) {
            claimRepository.delete(claim);
            LOG.info(claim + " deleted from claim repo by admin " + username);
        } else if (authType.equals("user")){
            if (claim.getFiledBy().equals(username)) {
                LOG.info(username + " has deleted their claim with ID " + claim.getId());
                claimRepository.delete(claim);
            } else {
                LOG.error("User tried to delete a claim not filed by themselves.");
                throw new RuntimeException("User cannot delete claims not filed by themselves");
            }
        }
    }

    public String emailText = "<!doctype html>\n" +
            "<html>\n" +
            "  <head>\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <title>Simple Transactional Email</title>\n" +
            "    <style>\n" +
            "@media only screen and (max-width: 620px) {\n" +
            "  table.body h1 {\n" +
            "    font-size: 28px !important;\n" +
            "    margin-bottom: 10px !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body p,\n" +
            "table.body ul,\n" +
            "table.body ol,\n" +
            "table.body td,\n" +
            "table.body span,\n" +
            "table.body a {\n" +
            "    font-size: 16px !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body .wrapper,\n" +
            "table.body .article {\n" +
            "    padding: 10px !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body .content {\n" +
            "    padding: 0 !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body .container {\n" +
            "    padding: 0 !important;\n" +
            "    width: 100% !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body .main {\n" +
            "    border-left-width: 0 !important;\n" +
            "    border-radius: 0 !important;\n" +
            "    border-right-width: 0 !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body .btn table {\n" +
            "    width: 100% !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body .btn a {\n" +
            "    width: 100% !important;\n" +
            "  }\n" +
            "\n" +
            "  table.body .img-responsive {\n" +
            "    height: auto !important;\n" +
            "    max-width: 100% !important;\n" +
            "    width: auto !important;\n" +
            "  }\n" +
            "}\n" +
            "@media all {\n" +
            "  .ExternalClass {\n" +
            "    width: 100%;\n" +
            "  }\n" +
            "\n" +
            "  .ExternalClass,\n" +
            ".ExternalClass p,\n" +
            ".ExternalClass span,\n" +
            ".ExternalClass font,\n" +
            ".ExternalClass td,\n" +
            ".ExternalClass div {\n" +
            "    line-height: 100%;\n" +
            "  }\n" +
            "\n" +
            "  .apple-link a {\n" +
            "    color: inherit !important;\n" +
            "    font-family: inherit !important;\n" +
            "    font-size: inherit !important;\n" +
            "    font-weight: inherit !important;\n" +
            "    line-height: inherit !important;\n" +
            "    text-decoration: none !important;\n" +
            "  }\n" +
            "\n" +
            "  #MessageViewBody a {\n" +
            "    color: inherit;\n" +
            "    text-decoration: none;\n" +
            "    font-size: inherit;\n" +
            "    font-family: inherit;\n" +
            "    font-weight: inherit;\n" +
            "    line-height: inherit;\n" +
            "  }\n" +
            "\n" +
            "  .btn-primary table td:hover {\n" +
            "    background-color: #34495e !important;\n" +
            "  }\n" +
            "\n" +
            "  .btn-primary a:hover {\n" +
            "    background-color: #34495e !important;\n" +
            "    border-color: #34495e !important;\n" +
            "  }\n" +
            "}\n" +
            "</style>\n" +
            "  </head>\n" +
            "  <body style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
            "    <span class=\"preheader\" style=\"color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;\">This is preheader text. Some clients will show this text as a preview.</span>\n" +
            "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f6f6f6; width: 100%;\" width=\"100%\" bgcolor=\"#f6f6f6\">\n" +
            "      <tr>\n" +
            "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\" valign=\"top\">&nbsp;</td>\n" +
            "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; max-width: 580px; padding: 10px; width: 580px; margin: 0 auto;\" width=\"580\" valign=\"top\">\n" +
            "          <div class=\"content\" style=\"box-sizing: border-box; display: block; margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
            "\n" +
            "            <!-- START CENTERED WHITE CONTAINER -->\n" +
            "            <table role=\"presentation\" class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background: #ffffff; border-radius: 3px; width: 100%;\" width=\"100%\">\n" +
            "\n" +
            "              <!-- START MAIN CONTENT AREA -->\n" +
            "              <tr>\n" +
            "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\" valign=\"top\">\n" +
            "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\" width=\"100%\">\n" +
            "                    <tr>\n" +
            "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\" valign=\"top\">\n" +
            "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">Hi there,</p>\n" +
            "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">Your auto insurance claim has been updated. Please log-in to check it out.</p>\n" +
            "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; box-sizing: border-box; width: 100%;\" width=\"100%\">\n" +
            "                          <tbody>\n" +
            "                            <tr>\n" +
            "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\" valign=\"top\">\n" +
            "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
            "                                  <tbody>\n" +
            "                                    <tr>\n" +
            "                                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; border-radius: 5px; text-align: center; background-color: #3498db;\" valign=\"top\" align=\"center\" bgcolor=\"#3498db\"> <a href=\"http://localhost:3000\" target=\"_blank\" style=\"border: solid 1px #3498db; border-radius: 5px; box-sizing: border-box; cursor: pointer; display: inline-block; font-size: 14px; font-weight: bold; margin: 0; padding: 12px 25px; text-decoration: none; text-transform: capitalize; background-color: #3498db; border-color: #3498db; color: #ffffff;\">See you Claims</a> </td>\n" +
            "                                    </tr>\n" +
            "                                  </tbody>\n" +
            "                                </table>\n" +
            "                              </td>\n" +
            "                            </tr>\n" +
            "                          </tbody>\n" +
            "                        </table>\n" +
            "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">Thank you for being our loyal customer. Pay us more money.</p>\n" +
            "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">Hope you didn't get denied!</p>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                  </table>\n" +
            "                </td>\n" +
            "              </tr>\n" +
            "\n" +
            "            <!-- END MAIN CONTENT AREA -->\n" +
            "            </table>\n" +
            "            <!-- END CENTERED WHITE CONTAINER -->\n" +
            "\n" +
            "            <!-- START FOOTER -->\n" +
            "            <div class=\"footer\" style=\"clear: both; margin-top: 10px; text-align: center; width: 100%;\">\n" +
            "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\" width=\"100%\">\n" +
            "                <tr>\n" +
            "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; color: #999999; font-size: 12px; text-align: center;\" valign=\"top\" align=\"center\">\n" +
            "                    <span class=\"apple-link\" style=\"color: #999999; font-size: 12px; text-align: center;\">JOHN ALBESA Inc, 1600 Pennsylvania Avenue NW, Washington, DC 20500</span>\n" +
            "                    <br> Don't like these emails? <a href=\"http://i.imgur.com/CScmqnj.gif\" style=\"text-decoration: underline; color: #999999; font-size: 12px; text-align: center;\">Unsubscribe</a>.\n" +
            "                  </td>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                  <td class=\"content-block powered-by\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; color: #999999; font-size: 12px; text-align: center;\" valign=\"top\" align=\"center\">\n" +
            "                    Powered by <a href=\"http://htmlemail.io\" style=\"color: #999999; font-size: 12px; text-align: center; text-decoration: none;\">HTMLemail</a>.\n" +
            "                  </td>\n" +
            "                </tr>\n" +
            "              </table>\n" +
            "            </div>\n" +
            "            <!-- END FOOTER -->\n" +
            "\n" +
            "          </div>\n" +
            "        </td>\n" +
            "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\" valign=\"top\">&nbsp;</td>\n" +
            "      </tr>\n" +
            "    </table>\n" +
            "  </body>\n" +
            "</html>";
}
