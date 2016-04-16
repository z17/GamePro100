var editor = ace.edit("ace_editor");

var renderer = PIXI.autoDetectRenderer(200, 200,{backgroundColor : 0x246784});
document.getElementById('game_container').appendChild(renderer.view);

// create the root of the scene graph
var stage = new PIXI.Container();

var container = new PIXI.Container();

stage.addChild(container);

for (var j = 0; j < 5; j++) {

	for (var i = 0; i < 5; i++) {
		var tile = PIXI.Sprite.fromImage('assets/tile.jpg');
		tile.x = 40 * i;
		tile.y = 40 * j;
		container.addChild(tile);
	};
};
var humanImage = PIXI.Texture.fromImage('assets/human.jpg');

// create a new Sprite using the texture
var human = new PIXI.Sprite(humanImage);

// center the sprite's anchor point
human.anchor.x = 0.5;
human.anchor.y = 0.5;

// move the sprite to the center of the screen
human.position.x = 100;
human.position.y = 100;

stage.addChild(human);

var doorImage = PIXI.Texture.fromImage('assets/door.jpg');

// create a new Sprite using the texture
var door = new PIXI.Sprite(doorImage);

// move the sprite to the center of the screen
door.position.x = 120;
door.position.y = 40;

stage.addChild(door);

container.x = 0;
container.y = 0;

// start animating
animate();

function animate() {

	if(human.position.x <= 220) {
		human.position.x = human.position.x + 1;
		human.position.y = human.position.y + 1;
	} else {
		human.position.x = -20;
		human.position.y = -20;
	}

	requestAnimationFrame(animate);

	// render the root container
	renderer.render(stage);
}
