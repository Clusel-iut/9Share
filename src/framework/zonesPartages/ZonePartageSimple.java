package framework.zonesPartages;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import framework.exception.Ensure;
import framework.exception.NotNullException;
import framework.exception.Require;
import framework.transferable.$Transferable;

/**
 * Description of ZonePartageSimple.
 * 
 * @author pignardf
 */
public class ZonePartageSimple implements java.io.Serializable{
	// Revoir la g�n�ricit� de la class
    private static final long serialVersionUID = 1L;
    protected ArrayList<$Transferable<?>> transferables = new ArrayList<$Transferable<?>>();

    /**
     * The constructor.
     * 
     * @throws RemoteException
     */
    public ZonePartageSimple() {
	super();
    }

    /**
     * Returns tous les transferable de la zone.
     * 
     * @return transferables
     */
    public <T> ArrayList<$Transferable<?>> getTransferables() {
	return this.transferables;
    }

    /**
     * Ajoute le transferable a la zone.
     * 
     * @param transferable
     * @throws NotNullException
     */
    public void addTransferable($Transferable<?> transferable)
	    throws NotNullException {
	if (transferable == null)
	    throw new NotNullException("$Transferable transferable",
		    "addTransferable");
	this.transferables.add(transferable);
	if (!(this.trouverTransferable(transferable)))
	    throw new Ensure("trouverTransferabke(transferable) == true",
		    "addTransferable");

    }

    /**
     * 
     * @param index
     * @return
     * @throws Require
     */
    public <T> T afficherMessageIndex(int index) throws Require {
	if (index >= this.transferables.size())
	    throw new Require("index < transferable.size()",
		    "afficherMessageIndex");
	return (T) this.transferables.get(index).getContenu();
    }

    /**
     * Cherche si un transferable est dans la zone.
     * 
     * @param transferable
     * @return true si le transferable existe dans la zone, false sinon.
     */
    private boolean trouverTransferable($Transferable<?> transferable) {
	Iterator<$Transferable<?>> it = this.getTransferables().iterator();
	while (it.hasNext()) {
	    if (it.next().equals(transferable)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Cherche si un transferable est dans la zone.
     * 
     * @param nom
     * @return le transferable trouv� ou null sinon.
     */
    public $Transferable<?> trouverTransferable(String nom) {
	Iterator<$Transferable<?>> it = this.getTransferables().iterator();
	$Transferable<?> result = null;
	while (it.hasNext()) {
	    result = it.next();
	    // TODO change to don't need exactly the same name
	    // TODO change to use contenu name and not toString
	    if (result.getContenu().toString().equals(nom)) {
		return result;
	    }
	}
	return result;
    }

    /**
     * Permet de connaitre la taille de la zone, sont nombre de transf�rable.
     * 
     * @return sont nombre de transf�rable.
     */
    public int size() {
	if (this.transferables == null)
	    return 0;
	return this.transferables.size();
    }

    /**
     * Permet de supprimer un transferable sur la zone.
     * 
     * @param t
     */
    public void supprimer($Transferable<?> t) {
	this.transferables.remove(this.transferables.indexOf(t));
    }
}
