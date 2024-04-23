
export class GenericUtilities {
	
    static isNullOrUndefined = (arg) => {
       return (arg === undefined || arg === null);
   }
   
    static isAnyUndefined = (...vars) =>{
    if(vars.length == 0){
      return false;	
     }
     vars.forEach(function (item, index) {
       if(isNullOrUndefined(item)){
           return true;
       }
     });
     return false;
   }
   
    static isValidSSN = (ssnCandidate) => {
       if(isNullOrUndefined(ssnCandidate)){
           return false;
       }
       return /^\d{3}[\-\s]*\d{2}[\-\s]*\d{4}$/.test(ssnCandidate);
   }
   
   static isValidUSPhoneNumber = (candidate) => {
       if(isNullOrUndefined(candidate)){
           return false;
       }
       return /^\(?\d{3}\)?[\s\-]*\d{3}[\s\-]*\d{4}$/.test(candidate);
   }
   
    static generateRandomIntBetween = (min, max) => {
       let factor = (max - min + 1);
       let floor = min;
       return Math.floor(Math.random() * factor)+floor;
   }
   
    static generatePseudoRandomString = () => {
       let randomInt = generateRandomIntBetween(1,999999999);
       let epochTimeMs = Date.now();
       return epochTimeMs+""+randomInt;
   }
   
    static targetInValues = (target,values) => {
       if(values.length == 0){
           return false;
       }
       return values.includes(target);
   }
   
    static distanceBetweenPoints2D = (point1, point2) => {
       if(isAnyUndefined(point1,point2)){
           return null;
       }
       let distSqrd = ( (point2.x - point1.x)^2 )+ ( (point2.y - point1.y)^2)
       return Math.sqrt(distSqrd);
   }
   
   }//class