import java.util.*;
import java.util.concurrent.TimeUnit;

abstract class AbstractGiveSurprises {

    // TO DO: define fields here
    IBag container;
    int waitTime;

    public AbstractGiveSurprises(String type, int waitTime) {
        // TO DO:
        this.container = BagFactory.getInstance().makeBag(type);
        this.waitTime = waitTime;
    }

    public void put(ISurprise newSurprise) {
        // TO DO:
        container.put(newSurprise);

    }

    public void put(IBag surprises) {
        // TO DO:
        container.put(surprises);


    }

    public void give() {
        // TO DO:
        if (!container.isEmpty()) {
            ISurprise surprise = container.takeOut();
            surprise.enjoy();
            giveWithPassion();
            waitForSeconds(waitTime);
        } else {
            System.out.println("The container is empty!");
        }
    }

    public void giveAll() {
        // TO DO:
        while (!container.isEmpty()) {
            give();
        }
    }

    public boolean isEmpty() {
        // TO DO:
        return container.isEmpty();

    }

    abstract void giveWithPassion();

    private void waitForSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BagFactory implements IBagFactory {

    // TO DO: define fields
    private static BagFactory instance;


    private BagFactory() {
    }

    public static BagFactory getInstance() {
        // TO DO:
        if (instance == null) {
            instance = new BagFactory();
        }
        return instance;
    }

    @Override
    public IBag makeBag(String type) {
        // TO DO:
        switch (type.toUpperCase()) {
            case "FIFO":
                return new BagFIFO();
            case "LIFO":
                return new BagLIFO();
            case "RANDOM":
                return new BagRandom();
            default:
                throw new IllegalArgumentException("Unknown bag type: " + type);
        }
    }
}

class BagFIFO implements IBag {

    // TO DO: define fields
    private Queue<ISurprise> surprises;

    BagFIFO() {
        // TO DO:
        surprises = new LinkedList<>();

    }

    @Override
    public void put(ISurprise newSurprise) {
        // TO DO:
        surprises.offer(newSurprise);


    }

    @Override
    public void put(IBag bagOfSurprises) {
        // TO DO:
        while (!bagOfSurprises.isEmpty()) {
            ISurprise surprise = bagOfSurprises.takeOut();
            surprises.offer(surprise);
        }
    }

    @Override
    public ISurprise takeOut() {
        // TO DO:
        if (isEmpty()) {
            System.out.println("Bag is empty!");
            return null;
        }
        return surprises.poll();
    }

    @Override
    public boolean isEmpty() {
        // TO DO:
        return surprises.isEmpty();

    }

    @Override
    public int size() {
        // TO DO:
        return surprises.size();

    }
}

class BagLIFO implements IBag {

    // TO DO: define fields
    private Stack<ISurprise> surprises;


    BagLIFO() {
        // TO DO:
        surprises = new Stack<>();


    }

    @Override
    public void put(ISurprise newSurprise) {
        // TO DO:
        surprises.push(newSurprise);

    }

    @Override
    public void put(IBag bagOfSurprises) {
        // TO DO:
        while (!bagOfSurprises.isEmpty()) {
            ISurprise surprise = bagOfSurprises.takeOut();
            surprises.push(surprise);
        }
    }

    @Override
    public ISurprise takeOut() {
        // TO DO:
        if (isEmpty()) {
            System.out.println("Bag is empty!");
            return null;
        }
        return surprises.pop();
    }

    @Override
    public boolean isEmpty() {
        // TO DO:
        return surprises.isEmpty();

    }

    @Override
    public int size() {
        // TO DO:
        return surprises.size();

    }
}

class BagRandom implements IBag {

    // TO DO: define fields
    private List<ISurprise> surprises;


    BagRandom() {
        // TO DO:
        surprises = new ArrayList<>();

    }

    @Override
    public void put(ISurprise newSurprise) {
        // TO DO:
        surprises.add(newSurprise);

    }

    @Override
    public void put(IBag bagOfSurprises) {
        // TO DO:
        while (!bagOfSurprises.isEmpty()) {
            ISurprise surprise = bagOfSurprises.takeOut();
            surprises.add(surprise);
        }
    }

    @Override
    public ISurprise takeOut() {
        // TO DO:
        if (isEmpty()) {
            System.out.println("Bag is empty!");
            return null;
        }
        Collections.shuffle(surprises);
        return surprises.remove(0);
    }

    @Override
    public boolean isEmpty() {
        // TO DO:
        return surprises.isEmpty();

    }

    @Override
    public int size() {
        // TO DO:
        return surprises.size();

    }
}

class Candies implements ISurprise {
    int num;
    String type;
    // TO DO: define fields
    private final static String [] candies = {"a", "b", "c", "d", "e", "f"};
    public Candies(int num, String type) {
        // TO DO:
        this.num = num;
        this.type = type;
    }

    @Override
    public void enjoy() {
        // TO DO:
        System.out.println("You received " + this.num + " "+ this.type+ " candies.");
    }

    @Override
    public String toString() {
        // TO DO:
        return ("[Candies] number = " + this.num+ ", type = " + this.type);

    }

    public static Candies generate() {
        // TO DO:
        Random random = new Random(candies.length);
        int indexTip = random.nextInt();
        int indexNr = random.nextInt();
        return new Candies(indexNr, candies[indexTip]);

    }
}

class FortuneCookie implements ISurprise {
    String message;
    private static final String[] quotes = {
            "Cand vei fi multumit sa fii pur si simplu tu insuti si sa nu te compari cu ceilalti, toti te vor respecta. (Lao Tse)",
            "Odata ce ai ales speranta, totul este posibil. (Christopher Reeve)",
            "Am invatat tacerea de la cei vorbareti, toleranta de la cei intoleranti si bunatatea de la cei rai. (Khalil Gibran)",
            "Cel ce n-are nici un merit invidiaza intotdeauna meritele altora. (Francis Bacon)",
            "Cea mai inalta masura a valorii o dai de fapt in timpul confruntarilor la care te supune viata. (Anonim)",
            "Am descoperit ca daca iubesti viata, si viata te va iubi pe tine. (Arthur Rubinstein)",
            "Fiecare experienta de viata, fiecare lectie pe care o invat este cheia spre viitorul meu. (Clark Gable)",
            "Prima conditie pentru a fi fericit este sa n-ai timp sa te gandesti la nefericire. (George Bernard Shaw)",
            "Da tot ce ai mai bun in tine, intrucat totul din viata ta iti apartine numai tie. (Ralph Waldo Emerson)",
            "Nebun este cine traieste in propria-i lume. (Paulo Coelho)",
            "Pentru orice rau exista doua leacuri: timpul si tacerea. (Alexandre Dumas)",
            "In viata lucrurile se fac usor, doar ideea gresita ca ar fi greu ne opreste sa fim extraordinari. (Marian Rujoiu)",
            "S-ar putea ca actiunea sa nu-ti ofere fericire, dar nu exista fericire in lipsa actiunii. (William James)",
            "Nu vad niciodata ce s-a facut. Vad doar ce mai e de facut. (Buddha)",
            "Nu-ti pierde timpul batand intr-un perete sperand ca il vei transforma intr-o usa. (Coco Chanel)",
            "Ataseaza-te de cei care te pot face mai bun si primeste-i pe cei care, la randul tau, ii poti face mai buni. (Seneca)",
            "Mai bine sa te consumi decat sa ruginesti. (Denis Diderot)",
            "Intelept este acela care traieste in fiecare zi ca si cum in fiecare zi si in fiecare ceas ar putea sa moara. (Francisco Gomez de Quevedo)",
            "Toti avem in noi o nestiuta rezerva de energie care izbucneste cand viata ne pune la incercare. (Isabel Allende)",
            "Stelele nu pot straluci fara intuneric. (Anonim)"
    };

    // TO DO: define fields

    public FortuneCookie(String message) {
        // TO DO:
        this.message = message;
    }

    @Override
    public void enjoy() {
        // TO DO:
        System.out.println("You got a fortune cookie!");
        System.out.println("\""+message+"\"");
    }

    @Override
    public String toString() {
        // TO DO:
        StringBuilder sb = new StringBuilder();
        sb.append("[FortuneCookie] message = ");
        sb.append(this.message);
        return sb.toString();
        // return("[FortuneCookie] message = " + this.message+"\"");
    }

    public static FortuneCookie generate() {
        // TO DO:
        Random random = new Random(quotes.length);
        int index = random.nextInt();
        return new FortuneCookie(quotes[index]);
    }
}

final class GatherSurprises {

    // TO DO: define fields

    private GatherSurprises() {
    }

    public static ISurprise[] gather(int n) {
        // TO DO:
        ISurprise[] surprises = new ISurprise[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int randomType = random.nextInt(3);
            surprises[i] = generateSurprise(randomType);
        }

        return surprises;
    }

    public static ISurprise gather() {
        // TO DO:
        Random random = new Random();
        int randomType = random.nextInt(3);
        return generateSurprise(randomType);
    }

    private static ISurprise generateSurprise(int type) {
        switch (type) {
            case 0:
                return FortuneCookie.generate();
            case 1:
                return Candies.generate();
            case 2:
                return MinionToy.generate();
            default:
                return null;
        }
    }
}

class GiveSurpriseAndApplause extends AbstractGiveSurprises {

    public GiveSurpriseAndApplause(String type, int waitTime) {
        // TO DO:
        super(type, waitTime);

    }

    @Override
    void giveWithPassion() {
        // TO DO:
        System.out.println("Loud applause to you... For it is in giving that we receive.");

    }
}

class GiveSurpriseAndHug extends AbstractGiveSurprises {

    public GiveSurpriseAndHug(String type, int waitTime) {
        // TO DO:
        super(type, waitTime);

    }

    @Override
    void giveWithPassion() {
        // TO DO:
        System.out.println("Warm wishes and a big hug!");

    }
}

class GiveSurpriseAndSing extends AbstractGiveSurprises {

    public GiveSurpriseAndSing(String type, int waitTime) {
        // TO DO:
        super(type, waitTime);

    }

    @Override
    void giveWithPassion() {
        // TO DO:
        System.out.println("Singing a nice song, full of joy and genuine excitement...");

    }
}

interface IBagFactory {

    // TO DO: write interface functions
    IBag makeBag(String type);

}

interface IBag {
    void put(ISurprise newSurprise);
    void put(IBag bagOfSurprises);
    ISurprise takeOut();
    boolean isEmpty();
    int size();
    // TO DO: write interface functions
}

interface ISurprise {

    // TO DO: write interface functions
    void enjoy();
}

class MinionToy implements ISurprise {

    private static final String[] names = { "Dave", "Carl", "Kevin", "Stuart", "Jerry", "Tim" };

    // TO DO: define fields
    static int count=0;
    String name;
    public MinionToy(String name) {
        // TO DO:
        this.name = name;
    }

    @Override
    public void enjoy() {
        // TO DO:
        System.out.println("You got a minion named " + this.name + "!");

    }

    @Override
    public String toString() {
        // TO DO:
        return "[Minion] name = " + this.name;
    }

    public static MinionToy generate() {
        // TO DO:
        count = count%6;
        return new MinionToy(names[count++]);

    }
}

 class Main {
    private static final int CONSTRUCT = 0;
    private static final int PUT_BAG = 1;
    private static final int PUT_SURPRIZE = 2;
    private static final int TAKEOUT = 3;
    private static final int SIZE = 4;
    private static final int GIVE = 5;
    private static final int GIVE_ALL = 6;

    private static final int FORTUNE_COOKIE = 0;
    private static final int CANDIES = 1;
    private static final int MINION_TOY = 2;
    private static final int BAG_FIFO = 3;
    private static final int BAG_LIFO = 4;
    private static final int BAG_RANDOM = 5;
    private static final int GIVE_SURPRISE_AND_APPLAUSE = 6;
    private static final int GIVE_SURPRISE_AND_HUG = 7;
    private static final int GIVE_SURPRISE_AND_SING = 8;

    private static Map<String, Integer> commandsMap = createCommandsMap();

    private static Map<String, Integer> createCommandsMap() {
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        myMap.put("construct", CONSTRUCT);
        myMap.put("putBag", PUT_BAG);
        myMap.put("putSurprize", PUT_SURPRIZE);
        myMap.put("takeout", TAKEOUT);
        myMap.put("size", SIZE);
        myMap.put("give", GIVE);
        myMap.put("giveAll", GIVE_ALL);

        return myMap;
    }

    private static Map<String, Integer> typesMap = createTypesMap();

    private static Map<String, Integer> createTypesMap() {
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        myMap.put("FortuneCookie", FORTUNE_COOKIE);
        myMap.put("Candies", CANDIES);
        myMap.put("MinionToy", MINION_TOY);
        myMap.put("BagFIFO", BAG_FIFO);
        myMap.put("BagLIFO", BAG_LIFO);
        myMap.put("BagRandom", BAG_RANDOM);
        myMap.put("GiveSurpriseAndApplause", GIVE_SURPRISE_AND_APPLAUSE);
        myMap.put("GiveSurpriseAndHug", GIVE_SURPRISE_AND_HUG);
        myMap.put("GiveSurpriseAndSing", GIVE_SURPRISE_AND_SING);
        return myMap;
    }

    private static final String[] quotes = {
            "Cand vei fi multumit sa fii pur si simplu tu insuti si sa nu te compari cu ceilalti, toti te vor respecta. (Lao Tse)",
            "Odata ce ai ales speranta, totul este posibil. (Christopher Reeve)",
            "Am invatat tacerea de la cei vorbareti, toleranta de la cei intoleranti si bunatatea de la cei rai. (Khalil Gibran)",
            "Cel ce n-are nici un merit invidiaza intotdeauna meritele altora. (Francis Bacon)",
            "Cea mai inalta masura a valorii o dai de fapt in timpul confruntarilor la care te supune viata. (Anonim)",
            "Am descoperit ca daca iubesti viata, si viata te va iubi pe tine. (Arthur Rubinstein)",
            "Fiecare experienta de viata, fiecare lectie pe care o invat este cheia spre viitorul meu. (Clark Gable)",
            "Prima conditie pentru a fi fericit este sa n-ai timp sa te gandesti la nefericire. (George Bernard Shaw)",
            "Da tot ce ai mai bun in tine, intrucat totul din viata ta iti apartine numai tie. (Ralph Waldo Emerson)",
            "Nebun este cine traieste in propria-i lume. (Paulo Coelho)",
            "Pentru orice rau exista doua leacuri: timpul si tacerea. (Alexandre Dumas)",
            "In viata lucrurile se fac usor, doar ideea gresita ca ar fi greu ne opreste sa fim extraordinari. (Marian Rujoiu)",
            "S-ar putea ca actiunea sa nu-ti ofere fericire, dar nu exista fericire in lipsa actiunii. (William James)",
            "Nu vad niciodata ce s-a facut. Vad doar ce mai e de facut. (Buddha)",
            "Nu-ti pierde timpul batand intr-un perete sperand ca il vei transforma intr-o usa. (Coco Chanel)",
            "Ataseaza-te de cei care te pot face mai bun si primeste-i pe cei care, la randul tau, ii poti face mai buni. (Seneca)",
            "Mai bine sa te consumi decat sa ruginesti. (Denis Diderot)",
            "Intelept este acela care traieste in fiecare zi ca si cum in fiecare zi si in fiecare ceas ar putea sa moara. (Francisco Gomez de Quevedo)",
            "Toti avem in noi o nestiuta rezerva de energie care izbucneste cand viata ne pune la incercare. (Isabel Allende)",
            "Stelele nu pot straluci fara intuneric. (Anonim)"
    };

    private static final String[] candyTypes = { "chocolate", "jelly", "fruits", "vanilla" };

    private static final String[] minionNames = { "Dave", "Carl", "Kevin", "Stuart", "Jerry", "Tim" };

    static FortuneCookie createFortuneCookie(String[] params, int index) {
        int qIndex = Integer.parseInt(params[index]);
        FortuneCookie fc = new FortuneCookie(quotes[qIndex % quotes.length]);
        return fc;
    }

    static Candies createCandies(String[] params, int index) {
        int num = Integer.parseInt(params[index++]);
        int tIndex = Integer.parseInt(params[index]);
        Candies candy = new Candies(num, candyTypes[tIndex % candyTypes.length]);
        return candy;
    }

    static MinionToy createMinionToy(String[] params, int index) {
        int mIndex = Integer.parseInt(params[index]);
        MinionToy minionToy = new MinionToy(minionNames[mIndex % minionNames.length]);
        return minionToy;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // dummy inits
        BagFIFO bagFifo = null;
        BagLIFO bagLifo = null;
        BagRandom bagRandom = null;
        ISurprise surprise = null;
        GiveSurpriseAndApplause giveSurpriseAndApplause = null;
        GiveSurpriseAndHug giveSurpriseAndHug = null;
        GiveSurpriseAndSing giveSurpriseAndSing = null;
        String bagType;
        IBag bag = null;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] params = line.split("\\s+");
            int i = 0;
            int waitTime = 0;
            String type;
            switch (commandsMap.get(params[i++])) {
                case CONSTRUCT:
                    switch (typesMap.get(params[i])) {
                        case FORTUNE_COOKIE:
                            FortuneCookie fc = createFortuneCookie(params, ++i);
                            System.out.println(fc.toString());
                            fc.enjoy();
                            break;
                        case CANDIES:
                            Candies c = createCandies(params, ++i);
                            System.out.println(c.toString());
                            c.enjoy();
                            break;
                        case MINION_TOY:
                            MinionToy minion = createMinionToy(params, ++i);
                            System.out.println(minion.toString());
                            minion.enjoy();
                            break;
                        case BAG_FIFO:
                            bagFifo = new BagFIFO();
                            break;
                        case BAG_LIFO:
                            bagLifo = new BagLIFO();
                            break;
                        case BAG_RANDOM:
                            bagRandom = new BagRandom();
                            break;
                        case GIVE_SURPRISE_AND_APPLAUSE:
                            type = params[++i];
                            waitTime = Integer.parseInt(params[++i]);
                            giveSurpriseAndApplause = new GiveSurpriseAndApplause(type, waitTime);
                            break;
                        case GIVE_SURPRISE_AND_HUG:
                            type = params[++i];
                            waitTime = Integer.parseInt(params[++i]);
                            giveSurpriseAndHug = new GiveSurpriseAndHug(type, waitTime);
                            break;
                        case GIVE_SURPRISE_AND_SING:
                            type = params[++i];
                            waitTime = Integer.parseInt(params[++i]);
                            giveSurpriseAndSing = new GiveSurpriseAndSing(type, waitTime);
                            break;
                    }
                    break;

                case PUT_BAG:
                    bagType = params[i];
                    i++;
                    switch (typesMap.get(params[i])) {
                        case BAG_FIFO:
                            bag = bagFifo;
                            break;
                        case BAG_LIFO:
                            bag = bagLifo;
                            break;
                        case BAG_RANDOM:
                            bag = bagRandom;
                            break;
                    }

                    switch (typesMap.get(bagType)) {
                        case BAG_FIFO:
                            bagFifo.put(bag);
                            break;
                        case BAG_LIFO:
                            bagLifo.put(bag);
                            break;
                        case BAG_RANDOM:
                            bagRandom.put(bag);
                            break;
                        case GIVE_SURPRISE_AND_APPLAUSE:
                            giveSurpriseAndApplause.put(bag);
                            break;
                        case GIVE_SURPRISE_AND_HUG:
                            giveSurpriseAndHug.put(bag);
                            break;
                        case GIVE_SURPRISE_AND_SING:
                            giveSurpriseAndSing.put(bag);
                            break;
                    }

                    break;

                case PUT_SURPRIZE:
                    bagType = params[i];
                    i++;
                    switch (typesMap.get(params[i])) {
                        case FORTUNE_COOKIE:
                            surprise = createFortuneCookie(params, ++i);
                            break;
                        case CANDIES:
                            surprise = createCandies(params, ++i);
                            break;
                        case MINION_TOY:
                            surprise = createMinionToy(params, ++i);
                            break;
                    }

                    switch (typesMap.get(bagType)) {
                        case BAG_FIFO:
                            bagFifo.put(surprise);
                            break;
                        case BAG_LIFO:
                            bagLifo.put(surprise);
                            break;
                        case BAG_RANDOM:
                            bagRandom.put(surprise);
                            break;
                        case GIVE_SURPRISE_AND_APPLAUSE:
                            giveSurpriseAndApplause.put(surprise);
                            break;
                        case GIVE_SURPRISE_AND_HUG:
                            giveSurpriseAndHug.put(surprise);
                            break;
                        case GIVE_SURPRISE_AND_SING:
                            giveSurpriseAndSing.put(surprise);
                            break;
                    }
                    break;

                case TAKEOUT:
                    switch (typesMap.get(params[i])) {
                        case BAG_FIFO:
                            surprise = bagFifo.takeOut();
                            surprise.enjoy();
                            break;
                        case BAG_LIFO:
                            surprise = bagLifo.takeOut();
                            surprise.enjoy();
                            break;
                        case BAG_RANDOM:
                            surprise = bagRandom.takeOut();
                            surprise.enjoy();
                            break;
                    }
                    break;

                case SIZE:
                    switch (typesMap.get(params[i])) {
                        case BAG_FIFO:
                            System.out.println(bagFifo.size());
                            break;
                        case BAG_LIFO:
                            System.out.println(bagLifo.size());
                            break;
                        case BAG_RANDOM:
                            System.out.println(bagRandom.size());
                            break;
                    }
                    break;

                case GIVE:
                    switch (typesMap.get(params[i])) {
                        case GIVE_SURPRISE_AND_APPLAUSE:
                            giveSurpriseAndApplause.give();
                            break;
                        case GIVE_SURPRISE_AND_HUG:
                            giveSurpriseAndHug.give();
                            break;
                        case GIVE_SURPRISE_AND_SING:
                            giveSurpriseAndSing.give();
                            break;
                    }
                    break;

                case GIVE_ALL:
                    switch (typesMap.get(params[i])) {
                        case GIVE_SURPRISE_AND_APPLAUSE:
                            giveSurpriseAndApplause.giveAll();
                            break;
                        case GIVE_SURPRISE_AND_HUG:
                            giveSurpriseAndHug.giveAll();
                            break;
                        case GIVE_SURPRISE_AND_SING:
                            giveSurpriseAndSing.giveAll();
                            break;
                    }
                    break;
            }
        }
    }
}
