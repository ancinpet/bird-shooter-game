package cz.cvut.fit.miadp.mvcgame.config;

public class MvcGameConfig 
{
	//Use realistic movement
	public static final boolean USE_REALISTIC_MODE = true;

	//Application area
    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;
    public static final int MAX_X = 1280;
	public static final int MAX_Y = 720;
	//How far up missiles can go before they disappear
    public static final int SKYBOX = 300;
	//Move speed
    public static final int MOVE_STEP = 10;
    //Cannon start position and hitbox
	public static final int CANNON_INIT_X = 20;
	public static final int CANNON_WIDTH = 25;
	public static final int CANNON_HEIGHT = 69;
	//Shooting cooldown
	public static final int CANNON_SHOOT_DELAY = 500;
	//Mode switch cooldown
	public static final int CANNON_SWITCH_DELAY = 300;
	//Cannon speed
	public static final int CANNON_MOVE_STEP = 5;
	//Enemy hitbox
	public static final int ENEMY_WIDTH = 30;
	public static final int ENEMY_HEIGHT = 30;
	//Missile hitbox
	public static final int MISSILE_WIDTH = 30;
	public static final int MISSILE_HEIGHT = 30;
	//Infobar start pos
	public static final int INFO_INIT_X = 10;
	public static final int INFO_INIT_Y = 20;	
	//How long to show collisions for
	public static final int COLLISION_DISPLAY_TIME = 1000;
	//Controls
	public static final String CONTROLS_FORWARD = "W";
	public static final String CONTROLS_BACKWARD = "S";
	public static final String CONTROLS_LEFT = "A";
	public static final String CONTROLS_RIGHT = "D";
	public static final String CONTROLS_MODE = "M";
	public static final String CONTROLS_SHOOT = "SPACE";
	public static final String CONTROLS_UNDO = "Z";
	public static final String CONTROLS_ANGLE_UP = "SHIFT";
	public static final String CONTROLS_ANGLE_DOWN = "CONTROL";
	public static final String CONTROLS_GRAVITY_UP = "UP";
	public static final String CONTROLS_GRAVITY_DOWN = "DOWN";
	public static final String CONTROLS_POWER_UP = "ADD";
	public static final String CONTROLS_POWER_DOWN = "SUBTRACT";
	//Experimental - undo is time based rather than action based (move within gameticks with undo)
	public static final boolean UNDO_TIME_BASED = true;
	//Enemies have 1 out of ENEMY_SPAWN_RATE chance to spawn enemy at gametick
	public static final int ENEMY_SPAWN_RATE = 100;
	//Extra angle for double shooting mode
	public static final int CANNON_DOUBLE_SIMPLE_ANGLE = 45;
	public static final int CANNON_DOUBLE_REALISTIC_ANGLE = 10;
	//Realistic enemy move one direction time
	public static final int ENEMY_REALISTIC_MOVE_ONE_DIR_TIME = 100;
	//How many kills for next level
	public static final int SCORE_TO_LEVEL = 10;
	//How many health
	public static final int GAME_HEALTH = 10;
}