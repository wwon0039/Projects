package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
//		World world = new World(new Display());
		
		NewWorld world = new NewWorld(new Display());
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		
		List<String> map2 = Arrays.asList(
		"+++++..............................+++++",
		"+++..................................+++",
		"+......................................+",
		".........#########...#########..........",
		".........#...................#..........",
		".........#...................#..........",
		"........................................",
		".........#...................#..........",
		".........#...................#..........",
		".........#########...#########..........",
		"+......................................+",
		"+++..................................+++",
		"+++++..............................+++++");
		
		List<String> map3 = Arrays.asList(
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................",
		"..................................................");
		
		
		
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		GameMap gameMap2 = new GameMap(groundFactory, map2 );
		world.addGameMap(gameMap2);
		
		GameMap gameMap3 = new GameMap(groundFactory, map3 );
		world.addGameMap(gameMap3);
		
		
		Vehicle car = new Vehicle("Car", '4', false);
		car.addAction(new MoveActorAction(gameMap2.at(6,6), "to the city."));
		gameMap.at(+10,10).addItem(car);
		
		Vehicle car2 = new Vehicle("Car", '4', false);
		car2.addAction(new MoveActorAction(gameMap.at(41,15), "to the hideout."));
		gameMap2.at(6,5).addItem(car2);
		
		gameMap.at(43,15).addItem(new Shotgun());
		gameMap3.at(22,11).addItem(new Shotgun());
		gameMap.at(43, 16).addItem(new SniperRifle());
		gameMap.at(42, 16).addItem(new ShotgunAmmo(2));
		gameMap.at(42, 17).addItem(new ShotgunAmmo(10));
		gameMap.at(44, 16).addItem(new SniperAmmo(5));


		
		Actor player = new Player("Player", '@', 1000000, gameMap);
		world.addPlayer(player, gameMap.at(42, 15));
		
		
//		Actor player = new Player("Player", '@', 100, gameMap2);
//		world.addPlayer(player, gameMap2.at(6, 6));
		
//		Actor player = new Player("Player", '@', 100, gameMap3);
//		world.addPlayer(player, gameMap3.at(22, 10));
		
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		
//		String[] humans = {"Carlton"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		
		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());
		gameMap.at(30, 20).addItem(new Plank());
		
		// FIXME: Add more zombies!
		gameMap.at(45, 10).addActor(new Zombie("Groan", gameMap));
		gameMap.at(30,  20).addActor(new Zombie("Boo", gameMap));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh", gameMap));
//		gameMap.at(50, 18).addActor(new Zombie("Mortalis", gameMap));
//		gameMap.at(1, 10).addActor(new Zombie("Gaaaah", gameMap));
//		gameMap.at(62, 12).addActor(new Zombie("Aaargh", gameMap));	
		
		//Trying to add farmers
		Farmer a = new Farmer("Jiajun", 'F', 50);
		Farmer b = new Farmer("Wai Chuin", 'F', 50);
//		gameMap.at(30,10).addActor(a);
//		System.out.println(a.hasCapability(ZombieCapability.ALIVE));
//		gameMap.at(30,21).addActor(b);
		
		
		MamboMarie boss = new MamboMarie("Marie");
//		gameMap.at(42,16).addActor(boss);
		world.run();
	}
}
