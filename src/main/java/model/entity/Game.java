package model.entity;

import controller.DBController;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "STATUS", nullable = false)
	private int status;


	//uno ad uno con modalità
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_mode",referencedColumnName = "id")
	private Mode mode;


	//uno ad uno con player, il player possiede la relazione
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_player", referencedColumnName = "id")
	private Player player;


	//uno ad uno con la mappa
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_map", referencedColumnName = "id")
	private Map map;

	public Game() {
	}

	public Game(int i, Mode modeDomain, Player playerDomain, Map mapDomain) {
		status=i;
		mode=modeDomain;
		player=playerDomain;
		map=mapDomain;
	}


	public long getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setId(long id) {
		this.id = id;
	}



	//viene chiamato ogni volta che il giocatore si muove
	public Resource triggerEvent(long idArea, Game gd) {
		DBController dbController=new DBController();
		List<Area> mapAreas = gd.getMap().getAreas();
		for (Area areas : mapAreas) {
			if(areas.getId() == idArea) {
				long idEvent = areas.getIdEvent();
				String category = areas.getCategory();
				if(Objects.equals(category, "RISORSA")) {
					return dbController.getResourceById(idEvent);
				} else {
					//dbController.getEnemyById(idEvent);
				}
			}
		}
		return null;
	}


	public boolean verifyAnswer(Event event, boolean answer) {
		// TODO - implement Game.verifyAnswer
		throw new UnsupportedOperationException();
	}

	public boolean move(int position) {
		DBController dbController = new DBController();
		Player p = this.getPlayer();
		int x_axis = p.getX_axis();
		int y_axis = p.getY_axis();
		Mode m = this.getMode();
		int range = (int) (m.getTotalArea() / 2) - 1;
		int rangey = 1;
		switch (position) {
			case 0:
				//nord x=x y=y-1
				if (y_axis - 1 >= 0 && y_axis - 1 <= rangey) {
					y_axis = y_axis - 1;
					//update player;
					p.setX_axis(x_axis);
					p.setY_axis(y_axis);
					p.setIdArea(p.setNewIdAreayVariant(y_axis,this));
					dbController.updatePlayer(p);
					return true;
				}
				return false;
			case 1:
				//est x=x+1 y=y
				if (x_axis + 1 >= 0 && x_axis + 1 <= range) {
					x_axis = x_axis + 1;
					//update player;
					p.setX_axis(x_axis);
					p.setY_axis(y_axis);
					p.setIdArea(p.setNewIdAreaxVariant(x_axis, this));
					dbController.updatePlayer(p);
					return true;
				}
				return false;
			case 2:
				//sud x=x y=y+1
				if (y_axis + 1 >= 0 && y_axis + 1 <= rangey) {
					y_axis = y_axis + 1;
					//update player;
					p.setX_axis(x_axis);
					p.setY_axis(y_axis);
					p.setIdArea(p.setNewIdAreayVariant(y_axis, this));
					dbController.updatePlayer(p);
					return true;
				}
				return false;
			case 3:
				//ovest x=x-1 y=y
				if (x_axis - 1 >= 0 && x_axis - 1 <= range) {
					x_axis = x_axis - 1;
					//update player;
					p.setX_axis(x_axis);
					p.setY_axis(y_axis);
					p.setIdArea(p.setNewIdAreaxVariant(x_axis, this));
					dbController.updatePlayer(p);
					return true;
				}
				return false;
		}
		return false;
	}

	public boolean pickUp(Resource res, Player player) {
		Inventory id = player.getInventory();
		DBController dbController=new DBController();
		if (id.checkCapacity(id)) {
			return dbController.updateInventory(res, id);
		}
		return false;
	}
}