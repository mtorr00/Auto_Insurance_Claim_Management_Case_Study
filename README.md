# Auto_Insurance_Claim_Management_Case_Study
A full stack web application that interfaces with MySQL (attempted AWS RDS) database storing insurance claim information, user and admin profiles, with asynchronous emailing.

# User Capability Goals
Signing up with an email + verification through said email allows users to then fill out a form to submit an auto insurance claim including S3 bucket storage of uploadable supporting documents.
Users can then receive emails notifying them to changes to their claim submissions.

# Admin Capability Goals
Admin enabled profiles can see all claims in the database and mark them Approved, Rejected, or keep them pending. 
Admin role will be hard coded initially, with admin login allowing setup for other admin logins.

# Technologies used:
 - Spring Security
 - Spring Java Mail
 - Yaml resource configuration
 - Lombok
 - Spring Boot
 - Node.js Maildev
 - Password Encoding
 - TODO: AWS S3 file uploading and filename encryption, React redirected front end after proper login, TailwindCSS and Anime.js to make front end POP, Unit Testing.

# Requirements
    •	Register an Insurance claim along with insured details and attach one or more necessary supporting documents (system should allow only PDF and Word)
    •	View Claim and supporting documents
    •	Mark a particular claim as “Approved or Rejected”
    •	Notification (optional): Asynchronous email notification to Insured on claim status change
    
    API:
    Proper use of HTTP methods, Parameters, Headers & response codes
    JSON should be used for request and response with proper input validation.
    Basic Authentication
    Proper exception handling
    Average /Maximum number of requests to be processed: 10/50 request per sec.
    98 % real-time operation should be completed within min 1 sec to max 5 sec

    Data Base:
    RDBMS design and DDL script
    ORM implementation with Transaction management
    
    FE:
    Responsive UI integrated with APIs (Angular or React)
    Common UI best practices (forms, CSS, validation, proper content including success / failure messages, spacing… etc.)
    
    Async Processing:
    Fire and forget implementation using naked thread implementation or messaging queues 
    Date loss prevention in case of exceptional scenario (Reliable messaging)

    Unit testing:
    Unit testing (Automation using Framework i.e., Junit, NUnit.. etc., Test case depth i.e. coverage, addressing positive, negative scenarios… etc.)
    
    User roles:
    Have a static login page with hardcoded role/s (should be based on json configuration)
