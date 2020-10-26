import java.util.ArrayList;
import java.util.Random;

//TODO add the rest of the chat icons

public class ChatIcons {

	private ArrayList<String> commands = new ArrayList<>();
	
	public ChatIcons() {
		commands.add("​:​COSfall19zchelf: ");
		commands.add(":​COSfall19jackhawk: ");
		commands.add(":alexmyer: ");
		commands.add(":alexmyercall: ");
		commands.add(":alexmyerwoahshi: ");
		commands.add(":approved: ");
		commands.add(":babyyoda: ");
		commands.add(":babyyodayay: ");
		commands.add(":baker99: ");
		commands.add(":batthink: ");
		commands.add(":blink: ");
		commands.add(":boss: ");
		commands.add(":brfunk: ");
		commands.add(":camerastare: ");
		commands.add(":COMeanjohnOCT: ");
		commands.add(":COMnov19bpennant: ");
		commands.add(":COMnov19gragleas: ");
		commands.add(":confusedog: ");
		commands.add(":corg: ");
		commands.add(":COSsum19ezingg: ");
		commands.add(":COSsum19maxpugh: ");
		commands.add(":COSsum19tuckwald: ");
		commands.add(":COSsum19tuckwald2: ");
		commands.add(":crimcard: ");
		commands.add(":datboiFAST: ");
		commands.add(":deal: ");
		commands.add(":denied: ");
		commands.add(":dog: ");
		commands.add(":elspenc: ");
		commands.add(":everythingsonfire: ");
		commands.add(":evilpat: ");
		commands.add(":eyes: ");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
//		commands.add("");
	}
	
	public String getRandomChatIcon() {
		Random rand = new Random();
		int randomIndex = rand.nextInt(commands.size());
		return commands.get(randomIndex);
	}
}
