var codeContainer = document.getElementById('code_editor');
var myCodeMirror = CodeMirror(codeContainer, {
	value: "World world = new World();\nworld.init();\n",
	lineNumbers: true,
	matchBrackets: true,
	mode: "text/x-java"
});
myCodeMirror.setOption("theme", 'icecoder');
myCodeMirror.setSize($(codeContainer).width(), $(codeContainer).height() );



var WORLD_MAP = [
	[' ',' ',' ','B',' ','t'],
	[' ','W','W','W',' ',' '],
	[' ',' ',' ',' ',' ','B'],
	[' ',' ','B','B','s',' '],
	[' ','B',' ',' ','D',' ']
];

var TILE_SIZE = 50,
	MAP_WIDTH = WORLD_MAP[0].length,
	MAP_HEIGHT = WORLD_MAP.length;

function renderObjectToMap(imagePath, coordX, coordY, container) {
	var objectImage = PIXI.Texture.fromImage(imagePath);

// create a new Sprite using the texture
	var object = new PIXI.Sprite(objectImage);

// move the sprite to the center of the screen
	object.position.x = TILE_SIZE * coordX;
	object.position.y = TILE_SIZE * coordY;

	container.addChild(object);
}

function getPathByTerrainChar(terrainChar) {
	var path = 'assets/';
	switch (terrainChar) {
		case ' ':
			path +=  'tile.png';
			break;
		case 'W': //wall
			path +=  'wall.png';
			break;
		case 'B': //block
			path +=  'stop.png';
			break;
		case 't': //finish
			path +=  'target.png';
			break;
		case 's': //start
			path +=  'tile.png';
			break;
		default :
			path +=  'tile.png';
			break;
	}
	return path;
}
var renderer = PIXI.autoDetectRenderer(TILE_SIZE * MAP_WIDTH, TILE_SIZE * MAP_HEIGHT, {backgroundColor : 0xECF0F1});
document.getElementById('game_container').appendChild(renderer.view);

// create the root of the scene graph
var stage = new PIXI.Container();

var container = new PIXI.Container();

stage.addChild(container);

var startPosition = {
	x: 0,
	y: 0
};

for (var line in WORLD_MAP) {
	for (var column in WORLD_MAP[line]) {
		var terrainChar = WORLD_MAP[line][column],
			imagePath = getPathByTerrainChar(terrainChar);
		renderObjectToMap(imagePath, column, line, container);
		if (terrainChar === 's') {
			startPosition.x = column;
			startPosition.y = line;
		}
	}
}


var humanImage = PIXI.Texture.fromImage('assets/jenya.png');

// create a new Sprite using the texture
var human = new PIXI.Sprite(humanImage);

human.anchor.y = 0.3;
// move the sprite to the center of the screen
human.position.x = TILE_SIZE * startPosition.x;
human.position.y = TILE_SIZE * startPosition.y;

stage.addChild(human);



container.x = 0;
container.y = 0;

// start animating
animate();

function animate() {
	requestAnimationFrame(animate);

	// render the root container
	renderer.render(stage);
}

function moveObject(object, direction) {

	var targetPosition = 0;

	switch (direction) {
		case 'right':
			var targetPosition = object.position.x + TILE_SIZE;
			break;
		case 'left':
			var targetPosition = object.position.x - TILE_SIZE;
			break;
		case 'bottom':
			var targetPosition = object.position.y + TILE_SIZE;
			break;
		case 'top':
			var targetPosition = object.position.y - TILE_SIZE;
			break;
	}
	animateObject();

	function animateObject() {

		switch (direction) {
			case 'right':
				object.position.x = object.position.x + 1;
				break;
			case 'left':
				object.position.x = object.position.x - 1;
				break;
			case 'bottom':
				object.position.y = object.position.y + 1;
				break;
			case 'top':
				object.position.y = object.position.y - 1;
				break;
		}

		var animationID = requestAnimationFrame(animateObject);
		renderer.render(stage);

		if (direction === 'right' && human.position.x >= targetPosition ||
			direction === 'left' && human.position.x <= targetPosition ||
			direction === 'bottom' && human.position.y >= targetPosition ||
			direction === 'top' && human.position.y <= targetPosition) {
			cancelAnimationFrame(animationID);
		}
	}
}

function dieDieDieMyDarling(object) {
	object.anchor.y = 0.5;
	object.anchor.y = 0.5;

	var timer = 200;

	animateObject();

	function animateObject() {
		timer--;
		var animationID = requestAnimationFrame(animateObject);
		if(timer > 150) {
			object.position.y -= 2;
			object.rotation -= 0.1;
		} else if (timer > 100) {
			object.rotation -= 0.5;
			object.position.y -= 2;
			object.position.x -= 0.5;
		} else if (timer > 50) {
			object.rotation -= 0.5;
			object.position.y += 2;
		} else if (timer > 0) {
			object.position.y += 2;
		} else {
			cancelAnimationFrame(animationID);
		}


	}
}