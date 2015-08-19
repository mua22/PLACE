package epclaim.utils;

import java.util.prefs.Preferences;

public class EpclaimPrefrences {
	private Preferences prefs;
	
	public static void main(String[] args) {
		EpclaimPrefrences pr = new EpclaimPrefrences();
		pr.setPreference();

	}
	
	public EpclaimPrefrences() {
		 prefs = Preferences.userRoot().node(this.getClass().getName());
		//super();
	}

	private String server = "localhost";
	private int PORT = 0;
	private String defaultFolder = ".";
	public void setPreference() {   
		this.setServer(this.getServer());
		this.setPORT(this.getPORT());
		//this.setPORT(this.getPORT());
		this.setDefaultFolder(this.getDefaultFolder());
	  }
	public String getServer() {
		return prefs.get("EpclaimServer", this.server);
	}
	public void setServer(String server) {
		prefs.put("EpclaimServer", server);
	}
	public int getPORT() {
		return prefs.getInt("EpclaimDefaultFolder", this.PORT);
	}
	public void setPORT(int pORT) {
		prefs.putInt("EpclaimDefaultFolder", pORT);
	}
	public String getDefaultFolder() {
		return prefs.get("EpclaimDefaultFolder", this.defaultFolder);
	}
	public void setDefaultFolder(String defaultFolder) {
		prefs.put("EpclaimDefaultFolder", defaultFolder);
	}

}
