package roles;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains fields for each Role.
 * @author devin
 */
public class Registry {
	
	private final Map<String, Role> stringToRole;
	
	private Registry() {
		
		// initialize fields for every role
		alphaWolf = new AlphaWolf();
		apprenticeSeer = new ApprenticeSeer();
		auraSeer = new AuraSeer();
		bodyguard = new Bodyguard();
		curator = new Curator();
		cursed = new Cursed();
		doppelganger = new Doppelganger();
		dreamWolf = new DreamWolf();
		drunk = new Drunk();
		hunter = new Hunter();
		insomniac = new Insomniac();
		mason = new Mason();
		minion = new Minion();
		mysticWolf = new MysticWolf();
		paranormalInvestigator = new ParanormalInvestigator();
		prince = new Prince();
		revealer = new Revealer();
		robber = new Robber();
		seer = new Seer();
		sentinel = new Sentinel();
		tanner = new Tanner();
		troublemaker = new Troublemaker();
		villageIdiot = new VillageIdiot();
		villager = new Villager();
		werewolf = new Werewolf();
		witch = new Witch();
		
		// initialize a Map to get a Role from a name
		stringToRole = new HashMap<>();
		stringToRole.put(alphaWolf.getName(), alphaWolf);
		stringToRole.put(apprenticeSeer.getName(), apprenticeSeer);
		stringToRole.put(auraSeer.getName(), auraSeer);
		stringToRole.put(bodyguard.getName(), bodyguard);
		stringToRole.put(curator.getName(), curator);
		stringToRole.put(cursed.getName(), cursed);
		stringToRole.put(doppelganger.getName(), doppelganger);
		stringToRole.put(dreamWolf.getName(), dreamWolf);
		stringToRole.put(drunk.getName(), drunk);
		stringToRole.put(hunter.getName(), hunter);
		stringToRole.put(insomniac.getName(), insomniac);
		stringToRole.put(mason.getName(), mason);
		stringToRole.put(minion.getName(), minion);
		stringToRole.put(mysticWolf.getName(), mysticWolf);
		stringToRole.put(paranormalInvestigator.getName(), paranormalInvestigator);
		stringToRole.put(prince.getName(), prince);
		stringToRole.put(revealer.getName(), revealer);
		stringToRole.put(robber.getName(), robber);
		stringToRole.put(seer.getName(), seer);
		stringToRole.put(sentinel.getName(), sentinel);
		stringToRole.put(tanner.getName(), tanner);
		stringToRole.put(troublemaker.getName(), troublemaker);
		stringToRole.put(villageIdiot.getName(), villageIdiot);
		stringToRole.put(villager.getName(), villager);
		stringToRole.put(werewolf.getName(), werewolf);
		stringToRole.put(witch.getName(), witch);
	}
    
    //A singleton structure
    private static Registry instance = null;
    public static Registry getInstance() {
        if (instance ==null) {
            return instance = new Registry();
        } else {
            return instance;
        }
    }
    
    public final Role alphaWolf;
    public final Role apprenticeSeer;
    public final Role auraSeer;
    public final Role bodyguard;
    public final Role curator;
    public final Role cursed;
    public final Role doppelganger;
    public final Role dreamWolf;
    public final Role drunk;
    public final Role hunter;
    public final Role insomniac;
    public final Role mason;
    public final Role minion;
    public final Role mysticWolf;
    public final Role paranormalInvestigator;
    public final Role prince;
    public final Role revealer;
    public final Role robber;
    public final Role seer;
    public final Role sentinel;
    public final Role tanner;
    public final Role troublemaker;
    public final Role villageIdiot;
    public final Role villager;
    public final Role werewolf;
    public final Role witch;
   
    /**
     * Returns the Role that can be represented by the parameter s.
     * @param s The string to be converted into a role.
     * @return The Role that is represented by s.
     */
    public Role roleFromString(String s) {
    	return stringToRole.get(s);
    }
}
