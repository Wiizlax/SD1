
public class ConnexionInfo {
   
 	Ensemble<LoginInfo> ensembleDesConnectes;
 	// N'AJOUTEZ PAS D'AUTRES ATTRIBUTS!!!
 	// N'OUBLIEZ PAS DE COMPLETER LA METHODE HASHCODE() DE LA CLASSE LOGININFO
 	
 	public ConnexionInfo(){
		 ensembleDesConnectes = new EnsembleTableBooleens<>(999);
	}
 	
 	public	int nombreDeConnectes(){
 		return ensembleDesConnectes.taille();
	}

	// ajoute le loginInfo s'il n'est pas encore connecte
	public boolean connecter(LoginInfo loginInfo) {
		if (ensembleDesConnectes.contient(loginInfo)) return false;
		ensembleDesConnectes.ajouter(loginInfo);
		return true;
	}

	// retire le loginInfo s'il est connecte
	public boolean deconnecter(LoginInfo loginInfo){
		 if (ensembleDesConnectes.contient(loginInfo)){
			 ensembleDesConnectes.enlever(loginInfo);
			 return true;
		 }
		return false;
	}

	public boolean estConnecte(LoginInfo loginInfo){
		return ensembleDesConnectes.contient(loginInfo);
	}
	

}