<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<div style="border-bottom: 4px solid #7092BE;margin-top:30px;"><span style="font-size: 48px"><b>LOCAL RANKING</b></span> <span>지역별 top10 랭킹입니다.</span></div>
<table id="localRank">
	<tr>
		<td><b>지역을 선택해주세요</b><img src="resources/images/pointer.gif" width="32px"/>
			<div id="map" style="width: 470px; height: 700px;"></div></td>
		<td><iframe name="mapFrame" width="620px" height="900px" frameborder="0" scrolling="no"></iframe></td>
	</tr>
</table>
  <script type="text/javascript">
    var locations = [
      ['서울', 37.5651, 126.98955],
      ['경기도', 37.267167, 127.590292],
      ['강원도', 37.735837, 128.209315],
      ['충청도', 36.628503, 127.429344],
      ['경상도', 35.948647, 128.664734],
      ['전라도', 35.216705, 127.144185],
      ['제주도', 33.364805, 126.542671]
    ];
    
    var mapping = [
   			['seoulRanking'],
   			['gyeonggiRanking'],
   			['kangwonRanking'],
   			['chungcheongRanking'],
   			['gyeongsangRanking'],
   			['junlaRanking'],
   			['jejuRanking']
    ];
  
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 7,
      center: new google.maps.LatLng(36, 127.8),
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      scrollwheel: false
    });
    var infowindow = new google.maps.InfoWindow();
    var marker, i;
    
    for (i = 0; i < locations.length; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2], locations[i][3], 
        						locations[i][4], locations[i][5], locations[i][6], locations[i][7]),
        map: map
      });
      google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
      google.maps.event.addListener(marker, 'click', (function(marker, i) {
    	  return function() {
    		window.open(mapping[i][0], "mapFrame");
    	  }
      })(marker, i));
    }
  </script>
 