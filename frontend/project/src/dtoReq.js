

function Character(HP, MP, type){
	this.HP = HP, // property
	this.MP = MP,  // property
	this.type = type,  // property
	this.fight = function(target){  // method
		this.MP -= 10;
		target.HP -= 10;
    } 
}

Character.prototype.drive = function () {
    console.log(this.name + ' runs at ' + this.speed)
  };


export default new Character;