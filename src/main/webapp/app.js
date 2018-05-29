angular.module('planetClubs', [
  'planetClubs.services',
  'controllers.aboutusCtrl',
  'controllers.contactusCtrl',
  'controllers.partneryourclubCtrl',
  'controllers.homeCtrl',
  'controllers.resetpasswordCtrl',
  'controllers.registerclubCtrl',
  'controllers.corporatePartnerCtrl',
  'ngRoute'
]).
config(['$routeProvider', function($routeProvider) {
	
	
  $routeProvider.
	when("/", {templateUrl: "components/home/home.html", controller: "homeCtrl"}).
	when("/contactus", {templateUrl: "components/contactus/contactus.html", controller: "contactusCtrl"}).
	when("/partner", {templateUrl: "components/partner/partneryourclub.html", controller: "partneryourclubCtrl"}).
	when("/corporate", {templateUrl: "components/partner/corporatepartner.html", controller: "corporatePartnerCtrl"}).
	when("/aboutus", {templateUrl: "components/aboutus/aboutus.html", controller: "aboutusCtrl"}).
	when("/resetpassword", {templateUrl: "components/passwordreset/resetpassword.html", controller: "resetpasswordCtrl"}).
	when("/registerclub", {templateUrl: "components/registerclub/registerclub.html", controller: "registerclubCtrl"}).
	when("/addservice", {templateUrl: "components/service/addservice.html"}).
	when("/privacypolicy", {templateUrl: "components/general/privacypolicy.html"}).
	when("/pricing", {templateUrl: "components/general/pricing.html"}).
	when("/refundpolicy", {templateUrl: "components/general/refundpolicy.html"}).
	when("/tandc", {templateUrl: "components/general/tandc.html"}).
	when("/faqs", {templateUrl: "components/faqs/faqs.html"}).
	when("/verify", {templateUrl: "components/verify/verify.html"}).
	otherwise({redirectTo: '/'});
  

	
}]);