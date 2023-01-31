/*Figure it out and write object-oriented code and at the same time touch on fresh topics – exceptions and logging.

A set of classes describing the operation of a hypothetical mail system is given. To begin with,
let's look at the code describing all the entities used.

//Interface: An entity that can be sent by mail.
From such an entity, you can get a letter from whom and to whom.

public static interface Sendable {
    String getFrom();
    String getTo();
}


Sendable has two subclasses, united by the following abstract class:


//An abstract class that allows you to abstract the logic of storing
the source and recipient of the message in the corresponding fields of the class.

public static abstract class AbstractSendable implements Sendable {

    protected final String from;
    protected final String to;

    public AbstractSendable(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSendable that = (AbstractSendable) o;

        if (!from.equals(that.from)) return false;
        if (!to.equals(that.to)) return false;

        return true;
    }

}

The first class describes an ordinary letter containing only a text message.


//A letter that has a text that can be obtained using the `getMessage` method

public static class MailMessage extends AbstractSendable {

    private final String message;

    public MailMessage(String from, String to, String message) {
        super(from, to);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MailMessage that = (MailMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

}


The second class describes a postal package:

//A package whose contents can be obtained using the `getContent` method

public static class MailPackage extends AbstractSendable {
    private final Package content;

    public MailPackage(String from, String to, Package content) {
        super(from, to);
        this.content = content;
    }

    public Package getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MailPackage that = (MailPackage) o;

        if (!content.equals(that.content)) return false;

    return true;
    }

}


At the same time , the package itself is described by the following class:


//The class that defines the package. The package has a text description of the contents and an integer value.

public static class Package {
    private final String content;
    private final int price;

    public Package(String content, int price) {
        this.content = content;
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (price != aPackage.price) return false;
        if (!content.equals(aPackage.content)) return false;

        return true;
    }
}


Now let's look at the classes that simulate the work of the mail service:


//An interface that specifies a class that can handle a mail object in some way.

public static interface MailService {
    Sendable processMail(Sendable mail);
}

*//*
Класс, в котором скрыта логика настоящей почты
*//*
public static class RealMailService implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}


You need to describe a set of classes, each of which is a MailService:

1) UntrustworthyMailWorker is a class that simulates an unreliable mail worker who,
 instead of passing a mail object directly to the mail service, sequentially passes this object
 to a set of third parties, and then, in the end, passes the resulting object directly to the RealMailService instance.
 The UntrustworthyMailWorker must have a constructor from the MailService array (the result of calling processMail
 of the first element of the array is passed to the processMail input of the second element, etc. and the getRealMailService method,
 which returns a reference to an internal instance of RealMailService, it does not come massively in the constructor
 and is not configured from outside the class.

2) Spy is a spy who logs all mail correspondence that passes through his hands. The object is constructed from a Logger instance,
 with which the spy will report all actions. He only monitors MailMessage class objects and writes the following messages
 to the logger (in expressions, you need to replace the parts in curly brackets with the values of mail fields):
2.1) If "Austin Powers" is specified as the sender or recipient, what should be written to the log message with the WARNING level:
 Target mail correspondence detected: from {from} to {to} "{message}"
2.2) Else, you need to write a message to the log with the level INFORMATION: Regular correspondence: from {from} to {to}

3) Thief is a thief who steals the most valuable parcels and ignores everything else. The thief accepts the variable "int"
 in the constructor – the minimum cost of the parcel that he will steal. Also, this class must have the getStolenValue method,
 which returns the total cost of all parcels that it has stolen. Theft occurs as follows: instead of the parcel that came to the thief,
 he gives a new one, the same one, only with zero value and the contents of the parcel "stones instead of {content}".

4) Inspector – An inspector who monitors prohibited and stolen parcels and raises the alarm as an exception if a similar parcel
 has been found. If he notices a prohibited package with one of the prohibited contents ("weapons" and "banned substance"),
 then he throws an IllegalPackageException. If he finds a package containing stones (contains the word "stones"), then the alarm
 will sound in the form of a StolenPackageException. You must declare both exceptions yourself as unchecked exceptions.


All classes should be defined as public and static, since during the verification process your code will be substituted into an external class
 that is engaged in testing and verifying the structure. For convenience, declare some convenient constants in an external class
 and import the contents of the java.util.logging package. To determine whether a Sendable object is a parcel or a letter, use the instanceof operator.

public static final String AUSTIN_POWERS = "Austin Powers";
public static final String WEAPONS = "weapons";
public static final String BANNED_SUBSTANCE = "banned substance";
*/





//So my solution is below:





import java.util.logging.Level;
import java.util.logging.Logger;

public static class UntrustworthyMailWorker implements MailService {

    private final RealMailService realMailService = new RealMailService();
    private MailService[] ms;


    public UntrustworthyMailWorker(MailService[] ms) {
        this.ms = ms;
    }

    public MailService getRealMailService() {
        return realMailService;
    }

    public Sendable processMail(Sendable mail) {
        Sendable processed = mail;
        for (MailService m : ms) {
            processed = m.processMail(processed);
        }
        return realMailService.processMail(processed);
    }
}

public static class Spy implements MailService {

    private final Logger logger;

    public Spy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            MailMessage mailMessage = (MailMessage) mail;
            String from = mailMessage.getFrom();
            String to = mailMessage.getTo();
            String message = mailMessage.getMessage();
            String[] args = {from, to, message};

            if (from.equals(AUSTIN_POWERS) || to.equals(AUSTIN_POWERS)) {
                logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"", args);
            } else {
                logger.log(Level.INFO, "Usual correspondence: from {0} to {1}", args);
            }
        }
        return mail;
    }
}

public static class Thief implements MailService {

    private final int minMailPrice;
    private int stolenValue = 0;

    public Thief(int minMailPrice) {
        this.minMailPrice = minMailPrice;
    }

    public int getStolenValue() {
        return stolenValue;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            Package p = ((MailPackage) mail).getContent();
            if (p.getPrice() > minMailPrice) {
                stolenValue += p.getPrice();
                mail = new MailPackage(mail.getFrom(), mail.getTo(), new Package("stones instead of " + p.getContent(), 0));
            }
        }
        return mail;
    }
}

public static class Inspector implements MailService {
    @Override
    public Sendable processMail(Sendable mail) throws RuntimeException {
        if (mail instanceof MailPackage) {
            Package p = ((MailPackage) mail).getContent();
            if (p.getContent().contains(WEAPONS) || p.getContent().contains(BANNED_SUBSTANCE)) {
                throw new IllegalPackageException("Запрещенная посылка");
            } else if (p.getContent().contains("stones")) {
                throw new StolenPackageException("Украденная посылка");
            }
        }
        return mail;
    }
}

public static class IllegalPackageException extends RuntimeException {

    public IllegalPackageException(String message) {
        super(message);
    }
}

public static class StolenPackageException extends RuntimeException {

    public StolenPackageException(String message) {
        super(message);
    }
}