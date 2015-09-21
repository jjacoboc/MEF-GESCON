/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var originalPrimeFacesCw = PrimeFaces.cw;
PrimeFaces.cw = function(name, id, options, resource) {
    resource = resource || name.toLowerCase();
    originalPrimeFacesCw.apply(this, [name, id, options, resource]);
};