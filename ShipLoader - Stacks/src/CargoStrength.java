/**
 * The CargoStrength enum lists the strength values that a cargo container
 * can have, which are FRAGILE, MODERATE, or STURDY. There are rules to the 
 * container of the strength. FRAGILE containers can support only other 
 * FRAGILE containers. MODERATE containers can support FRAGILE and other 
 * MODERATE containers. STURDY containers can support any container including
 * other STURDY containers. 
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 *		Recitation#04
 */
public enum CargoStrength {
	FRAGILE, MODERATE, STURDY
}
