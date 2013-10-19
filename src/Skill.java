package deity.skills;

public class Skill {
	
	protected final String skillName;
	
	public final String getName() {
		return this.skillName;
	}
	
	public Skill(String name) {
		
		this.skillName = name;
	}

}