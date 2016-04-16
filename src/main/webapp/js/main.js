var codeContainer = document.getElementById('code_editor');
var myCodeMirror = CodeMirror(codeContainer, {
	value: "World world = new World();\nworld.init();\n",
	lineNumbers: true,
	matchBrackets: true,
	mode: "text/x-java"
});

var worldMap = [];
$.ajax({
	url: '/services/task/getMap?id=1',
	async: false
}).done(function (data) {
	worldMap = data;
});

var TILE_SIZE = 50,
	MAP_WIDTH = worldMap[0].length,
	MAP_HEIGHT = worldMap.length;

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
		case '.':
			path +=  'tile.png';
			break;
		case '*': //wall
			path +=  'wall.png';
			break;
		case 'B': //block
			path +=  'sTOP.png';
			break;
		case 'E': //finish
			path +=  'target.png';
			break;
		case 'S': //start
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

for (var line in worldMap) {
	for (var column in worldMap[line]) {
		var terrainChar = worldMap[line][column],
			imagePath = getPathByTerrainChar(terrainChar);
		renderObjectToMap(imagePath, column, line, container);
		if (terrainChar === 'S') {
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
		case 'RIGHT':
			var targetPosition = object.position.x + TILE_SIZE;
			break;
		case 'LEFT':
			var targetPosition = object.position.x - TILE_SIZE;
			break;
		case 'LEFT':
			var targetPosition = object.position.y + TILE_SIZE;
			break;
		case 'TOP':
			var targetPosition = object.position.y - TILE_SIZE;
			break;
	}
	animateObject();

	function animateObject() {

		switch (direction) {
			case 'RIGHT':
				object.position.x = object.position.x + 1;
				break;
			case 'LEFT':
				object.position.x = object.position.x - 1;
				break;
			case 'LEFT':
				object.position.y = object.position.y + 1;
				break;
			case 'TOP':
				object.position.y = object.position.y - 1;
				break;
		}

		var animationID = requestAnimationFrame(animateObject);
		renderer.render(stage);

		if (direction === 'RIGHT' && human.position.x >= targetPosition ||
			direction === 'LEFT' && human.position.x <= targetPosition ||
			direction === 'LEFT' && human.position.y >= targetPosition ||
			direction === 'TOP' && human.position.y <= targetPosition) {
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

$('.js-start').click(function () {
	$.ajax({
		url: '/services/task/submit/',
		data: { code: "  man.moveUp(); ", id: 1 }
	}).done(function (data) {
		var commands = data.split(';');
		for(var command in commands) {
			if(command === 'ERROR') {
				dieDieDieMyDarling(human);
			} else {
				moveObject(human, command);
			}
		}
	});
});