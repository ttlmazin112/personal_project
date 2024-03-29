/**
 * jQuery Syaku Rolling ver 1.3.0
 *
 * Copyright (c) Seok Kyun. Choi. 최석균
 * GNU Lesser General Public License
 * http://www.gnu.org/licenses/lgpl.html
 *
 * registered date 20110830
 * http://syaku.tistory.com
 */

(function($) {

  $.fn.srolling = function(settings) {

    settings = jQuery.extend({
      data : [ ], // 롤링될 아이템 데이터
      name : '#srolling_area', // 롤링 대상 element
      item_count : 1, // 한번 이동될 아이템 수
      cache_count : 5, // 최초 한번 임시로 생성될 아이템 수 (고치지마세요)
      width : 100, // 노출될 아이템 크기 (필수 : 실제 아이템 보다 약간 크게 설정하세요)
      height : 100, // 노출될 아이템 크기 (필수 : 실제 아이템 보다 약간 크게 설정하세요)
      auto : false, // 자동 이동 설정
      delay : 1000, // 이동 후 대기 시간
      delay_frame : 500, // 이동 속도
      move : 'left', // 이동 방향 left , right , up , down 
      prev : '#srolling_prev', // 이전 아이템 이동 버튼 element
      next : '#srolling_next', // 다음 아이템 이동 버튼 element
      is_bullet : false, // 블릿 사용여부
      bullet : '#srolling_bullet', // 블릿 버튼 element
      bullet_item : '#srolling_bullet_item', // 블릿 버튼 item element
      bullet_style_func : null // 선택된 블릿 효과주기
    }, settings);

    var name = settings.name;
    var auto = settings.auto;
    var auto_go = auto;
    var delay = settings.delay;
    var delay_time = delay *2;
    var delay_frame = settings.delay_frame
    var move = settings.move;
    var prev = settings.prev;
    var next = settings.next;
    var is_bullet = settings.is_bullet;
    var bullet = settings.bullet;
    var bullet_item = settings.bullet_item;
    var bullet_style_func = settings.bullet_style_func;

    var data = settings.data;
    var item_count = settings.item_count;
    var cache_count = settings.cache_count;
    var index = settings.index;
    var item_width = settings.width;
    var item_height = settings.height;

    var item_total = data.length; // 아이템 총수
    cache_count = (item_total > cache_count) ? item_total*2 : cache_count; // 캐쉬생성 수
    var prev_idx = item_total;
    var next_idx = 0;
    var start_left = item_width * (cache_count * -1);
    var w_full_size = (parseInt(item_width) * parseInt(item_total)) * 10;

    switch (move) {
      case 'down' : 
      case 'up' : 
        w_full_size = item_width; 
        start_left = item_height * (cache_count *-1);
      break;
    }

    var box = this;
    name = name + '_' + box.attr('id');
    var str_name = name.replace('#','');
    var box_area = box.append("<div id='" + str_name + "' style='width:" + w_full_size + "px;position: absolute;white-space:nowrap;'></div><!-- Copyright (c) Seok Kyun. Choi. 최석균 http://syaku.tistory.com -->");
    box_area = jQuery(name,box);
    var item_area = jQuery("<div></div>").css("width",item_width).css("height",item_height).css("float","left").css("overflow","hidden");

    var sTime = null;
    var sTime2 = null;
    var item_idx = 1; // 현재 아이템 순번

    function _initialize() {
      _items();

      // 블릿 여부
      if (is_bullet) _bullet_init();
      _bullet_style();

      if (auto) { _play(); }

      jQuery(prev).click(function() {
        _prev_act();
      });

      jQuery(next).click(function() {
        _next_act();
      });

      jQuery(box_area).mouseover(function() {
        if (auto) {
          clearTimeout(sTime);
          auto_go = false;
        }
      });

      jQuery(box_area).mouseout(function() {
        if (auto) {
          auto_go = true;
          _play();
        }
      });

      // html5 & jquerymobile
      box_area.bind('swipeleft', function(event) {
        _next_act();
      });
      box_area.bind('swiperight', function(event) {
        _prev_act();
      });

    }

    // 블릿 활성화
    function _bullet_init() {
      for (var i = 0; i < item_total; i++) {
        var bullet_item_btn = jQuery(bullet_item).clone();
        var obj = bullet_item_btn.removeAttr('id').addClass('syaku_r_bullet_'+i);
        var html = obj.html();
        obj.html(html.replace('{idx}',i+1));
        jQuery(bullet).append(obj.click(function() {
          var idx = jQuery(this).attr('class');
          idx = idx.replace('syaku_r_bullet_','');
          _bullet_act(Number(idx)+1);
        }));
      }

      jQuery(bullet_item).remove();
    }


    // 현재 아이템 idx 구하기
    function _item_idx(idx) {
      if (idx > 0) { item_idx++; }
      else { item_idx--; }
      if (item_idx > item_total) { item_idx = 1; }
      if (item_idx < 1){ item_idx = item_total; }
    }

    function _start_box() {
      switch (move) {
        case 'down' : 
        case 'up' : box_area.css('top',start_left); break;
        case 'right' : 
        case 'left' : box_area.css('left',start_left); break;
      }
    }

    function _play() {
      switch (move) {
        case 'down' : 
        case 'right' : sTime = setTimeout(_prev_auto_act,delay_time); break;
        case 'up' : 
        case 'left' : sTime = setTimeout(_next_auto_act,delay_time); break;
      }
    }

    function _prev_auto_act() {
      if (auto && auto_go) {
        _prev_act();
      }
    }
    
    function _next_auto_act() {
      if (auto && auto_go) {
        _next_act();
      }
    }

    function _prev_act() {
      _item_idx(-1);
      var prev_obj = jQuery(prev);
      var next_obj = jQuery(next);

      if (auto) { // 자동 실행 멈춤
        clearTimeout(sTime);
        auto_go = false;
      }

      prev_obj.unbind('click');
      next_obj.unbind('click');

      switch (move) {
        case 'left' : 
        case 'right' : 

          box_area.animate({
            left: '+=' + (item_width * item_count)
          }, delay_frame, function() { 
            _bullet_style();
            for (var i = 0; i < item_count; i++) { box_area.children().last().remove(); }
            _create_prev();
            _start_box();

            prev_obj.click(_prev_act);
            next_obj.click(_next_act);
            
            if (auto) { // 자동 이동 실행
              auto_go = true;
              _play();
            }

          });

        break;
        case 'up' : 
        case 'down' : 


          box_area.animate({
            top: '+=' + (item_height * item_count)
          }, delay_frame, function() { 
            _bullet_style();
            for (var i = 0; i < item_count; i++) { box_area.children().last().remove(); }
            _create_prev();
            _start_box();
            prev_obj.click(_prev_act);
            next_obj.click(_next_act);

            if (auto) { // 자동 이동 실행
              auto_go = true;
              _play();
            }

          });

        break;
      }

    }

    function _next_act() {
      _item_idx(1);
      var prev_obj = jQuery(prev);
      var next_obj = jQuery(next);

      if (auto) { // 자동 실행 멈춤
        clearTimeout(sTime);
        auto_go = false;
      }

      prev_obj.unbind('click');
      next_obj.unbind('click');
      
      switch (move) {
        case 'down' : 
        case 'up' : 
          box_area.animate({
            top: '-=' + (item_height * item_count)
          }, delay_frame, function() { 
            _bullet_style();
            for (var i = 0; i < item_count; i++) { box_area.children().first().remove(); }
            _create_next();
            _start_box();
            prev_obj.click(_prev_act);
            next_obj.click(_next_act);

            if (auto) { // 자동 이동 실행
              auto_go = true;
              _play();
            }

          });

        break;

        case 'right' : 
        case 'left' : 

        box_area.animate({
            left: '-=' + (item_width * item_count)
          }, delay_frame, function() {
            _bullet_style();
            for (var i = 0; i < item_count; i++) { box_area.children().first().remove(); }
            _create_next();
            _start_box();
            next_obj.click(_next_act);
            prev_obj.click(_prev_act);

            if (auto) { // 자동 이동 실행
              auto_go = true;
              _play();
            }

          });

        break;
      }

    }

    function _bullet_style() {
      if (jQuery.isFunction(bullet_style_func) && is_bullet) {
        bullet_style_func(jQuery(bullet),jQuery(bullet + ' .syaku_r_bullet_' + (item_idx-1)));
      }
    }
    function _bullet_act(get_idx) {
      var move_idx = 0;

      if (item_idx > get_idx) {
        move_idx = (item_total+get_idx) - item_idx
      } else {
        move_idx = get_idx - item_idx
      }

      if (move_idx > 0) {

        var prev_obj = jQuery(prev);
        var next_obj = jQuery(next);

        if (auto) { // 자동 실행 멈춤
          clearTimeout(sTime);
          auto_go = false;
        }

        prev_obj.unbind('click');
        next_obj.unbind('click');
        
        switch (move) {
          case 'down' : 
          case 'up' : 

            item_idx = get_idx; // 현재 아이템 설정
            var get_item = item_height * move_idx;

            box_area.animate({
              top: '-=' + get_item
            }, delay_frame, function() { 
              _bullet_style();
              for (var i = 0; i < move_idx; i++) { box_area.children().first().remove(); }
              _create_next(move_idx);
              _start_box();
              prev_obj.click(_prev_act);
              next_obj.click(_next_act);

              if (auto) { // 자동 이동 실행
                auto_go = true;
                _play();
              }

            });

          break;

          case 'right' : 
          case 'left' : 

            item_idx = get_idx; // 현재 아이템 설정
            var get_item = item_width * move_idx;

            box_area.animate({
            left: '-=' + get_item
            }, delay_frame, function() { 
            _bullet_style();
            for (var i = 0; i < move_idx; i++) { box_area.children().first().remove(); }
            _create_next(move_idx);
            _start_box();
            next_obj.click(_next_act);
            prev_obj.click(_prev_act);

            if (auto) { // 자동 이동 실행
            auto_go = true;
            _play();
            }

            });
          break;
        }

      }
    }

    function _items() {
      _start_box();
      
      for (var i = cache_count; i > 0; i--) {
        if (item_total <= prev_idx || prev_idx < 0) { prev_idx = item_total-1; }
        var t_obj = item_area.clone();
        box_area.prepend( t_obj.append(jQuery(data[prev_idx])).attr('class','srolling_item_' + prev_idx) );
        prev_idx--;
      }
      
      for (var i=0; i < cache_count; i++) {
        if (item_total <= next_idx || next_idx < 0) { next_idx = 0; }
        var t_obj = item_area.clone();
        box_area.append( t_obj.append(jQuery(data[next_idx])).attr('class','srolling_item_' + next_idx) );
        next_idx++;
      }

    }

    function _create_prev() {
      var p_obj = box_area.children().first().attr('class');
      prev_idx = parseInt(p_obj.replace('srolling_item_','')) - 1;

      for (var i = item_count; i > 0; i--) {
        if (item_total <= prev_idx || prev_idx < 0) { prev_idx = (item_total-1); }

        var t_obj = item_area.clone();
        box_area.prepend( t_obj.append(jQuery(data[prev_idx])).attr('class','srolling_item_' + prev_idx) );
        prev_idx--;
      }
    }

    function _create_next(c_num) {
      var create_num = item_count;
      if (!isNaN(c_num)) create_num = c_num;
      var n_obj = box_area.children().last().attr('class');
      next_idx = parseInt(n_obj.replace('srolling_item_','')) + 1;
      for (var i=0; i < create_num; i++) {
        if (item_total <= next_idx || next_idx < 0) { next_idx = 0; }

        var t_obj = item_area.clone();
        box_area.append( t_obj.append(jQuery(data[next_idx])).attr('class','srolling_item_' + next_idx) );
        next_idx++;
      }
    }

    _initialize();
  };
  
})(jQuery);

