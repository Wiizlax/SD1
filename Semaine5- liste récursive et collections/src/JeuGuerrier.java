
public class JeuGuerrier {
	
	public static void main(String[] args) {
		EquipeGuerriers equipe = new EquipeGuerriers(3, 10);
		int pointsDeVieDuMal = 30;
		
		// A COMPLETER
		int pvMalRestants = pointsDeVieDuMal;

		System.out.println("------------------------------------");
		System.out.println("Simulation d'un combat entre 3 valeureux guerriers et un terrible monstre...");
		System.out.println("------------------------------------");
		while (true) {
			System.out.println("L'equipe compte " + equipe.nombreGuerriersEnVie() + " guerriers en vie");
			int dps = lancerDe();
			Guerrier g = equipe.jouer(dps);
			System.out.println("Suite au combat entre la creature du mal et le guerrier n°" +g.getNumero() +"," );
			System.out.println("le guerrier vient de perdre "+dps+" points de vie");
			if (g.getPointsDeVie()<=0){
				System.out.println("Il reste 0 points de vie à ce valeureux guerrier");
				System.out.println("Le guerrier n°"+g.getNumero()+" est mort");
			}else{
				System.out.println("Il reste "+g.getPointsDeVie()+" points de vie à ce valeureux guerrier");
			}
			if (equipe.nombreGuerriersEnVie()==0){
				System.out.println("Tous les guerriers sont morts");
				System.out.println("Paix à leurs âmes...");
				break;
			}
			int dpsMal = lancerDe();
			System.out.println("Suite à un contre, la créature du mal vient de perdre "+dpsMal + "points de vie");
			pvMalRestants = pvMalRestants-dpsMal;
			if (pvMalRestants<=0){
				System.out.println("Il lui reste 0 points de vie ");
				System.out.println("La creature du mal est morte");
				System.out.println("Bravo à tous ces valeureux guerriers !");
				break;
			}
			System.out.println("Il lui reste "+pvMalRestants+"points de vie");
			System.out.println("   ");
			System.out.println("------------------------------------");
			System.out.println("   ");
		}
	}
	
	public static int lancerDe (){
		double nombreReel;
		nombreReel = Math.random();
		return (int) (nombreReel * 6) + 1;
	}
	
}
