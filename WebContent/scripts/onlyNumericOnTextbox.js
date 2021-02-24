/**
 * @author Tom Van Schoor
 * @company Tutuka Software
 */
(function($) {
  /**
   * @param {Object}
   * $$options options to override settings
   */
  jQuery.fn.inputmask = function($$options) {
    var $settings = $.extend( {}, $.fn.inputmask.defaults, $$options);

    return this.each(function() {
      // $this is an instance of the element you call the plug-in on
      var $this = $(this);

      /*
       * This plug-in does not depend on the metadata plug-in, but if this
       * plug-in detects the existence of the metadata plug-in it will
       * override options with the metadata provided by that plug-in. Look at
       * the metadata plug-in for more information.
       */
      // o will contain your defaults, overruled by $$options,
      // overruled by the meta-data
      var o = $.metadata ? $.extend( {}, $settings, $this.metadata()) : $settings;

      /*
       * if digits is in the array 'validators' provided by the options,
       * stack this event handler
       */
      if($.inArray('digits', o.validators) != -1) {
        $this.keyup(function(e) {
          $this.val(stripAlphaChars($this.val()));
        });
      }

      /*
       * There is no such things as public methods in jQuery plug-ins since
       * there is no console to perform commands from a client side point of
       * view. Typically only private methods will be fired by registered
       * events as on-click, on-drag, etc... Those registered events could be
       * seen as public methods.
       */

      // private method
      var stripAlphaChars = function(string) {
        var str = new String(string); 
        str = str.replace(/[^0-9]/g, ''); 
        return str;
      }

    });
  };

  // static public functions
  //jQuery.fn.inputmask.doSomething = function(attr) {

  //};

  // static public members
  //jQuery.fn.inputmask.someStaticPublicMember;

  // some default settings that can be overridden by either $$options or
  // metadata
  // If you need callback functions for the plug-in, this is where they get
  // set
  jQuery.fn.inputmask.defaults = {
    validators : []
  };
})(jQuery);
