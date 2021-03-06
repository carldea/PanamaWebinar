import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.unix.tm;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import static org.unix.foo_h.*;

/**
 * javac javac --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.web -classpath classes -d classes --enable-preview -source 19 src/PanamaNativeTimeFX.java
 * java --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.web -classpath classes PanamaNativeTimeFX
 */
public class PanamaNativeTimeFX extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Clock with Project Panama");
        final WebView browser = new WebView();
        browser.getEngine().loadContent(SVGClock2.SVG);

        Runnable updateClock = ()-> {
            try (var memorySession = MemorySession.openConfined()) {
                // Calling the C function localtime_r(now, time); now contains seconds, and time is a blank struct.
                // [ptr to struct]        [ptr to time_t           ]   [ptr to tm struct     ]
                // struct tm *localtime_r( const time_t * epochSeconds, struct tm * tmStruct );
                MemorySegment cNow = memorySession.allocate(time_t);
                MemorySegment pTmStruct = tm.allocate(memorySession);
                time(cNow);
                localtime_r(cNow, pTmStruct);
                var hours = tm.tm_hour$get(pTmStruct);
                hours = hours < 12 ? hours : hours - 12;
                var minutes = tm.tm_min$get(pTmStruct);
                var seconds = tm.tm_sec$get(pTmStruct);
                String updateTimeJs = "updateTime(%d, %d, %d);".formatted(hours, minutes, seconds);
                //System.out.println(updateTimeJs);
                browser.getEngine().executeScript(updateTimeJs);
            }
        };
        AnimationTimer h = new AnimationTimer() {
            private long lastUpdate; // Last time in which `handle()` was called
            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long now) {
                long elapsedNanoSeconds = now - lastUpdate;

                // 1 second = 1,000,000,000 nanoseconds
                double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0;
                if (elapsedSeconds >= 1) {
                    updateClock.run();
                    lastUpdate = now;
                }
            }

        };
        h.start();
        scene = new Scene(browser,320,250, Color.rgb(0, 0, 0, .80));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
class SVGClock2 {
    public final static String SVG = """  
            <svg
               xmlns:dc="http://purl.org/dc/elements/1.1/"
               xmlns:cc="http://creativecommons.org/ns#"
               xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
               xmlns:svg="http://www.w3.org/2000/svg"
               xmlns="http://www.w3.org/2000/svg"
               xmlns:xlink="http://www.w3.org/1999/xlink"
               xmlns:sodipodi="http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd"
               xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape"
               width="300"
               height="250"
               id="svg4171"
               version="1.1"
               inkscape:version="0.48.1 "
               sodipodi:docname="clock3.svg" onload="updateTime()">
                        
            <script>
                        
            <![CDATA[
            var xmlns="http://www.w3.org/2000/svg"
            function updateTime(hr, min, sec)
            {
              const pi=180
                        
              const secondAngle = sec * 6 + pi
              const minuteAngle = ( min + sec / 60 ) * 6 + pi
              const hourAngle   = (hr + min / 60 + sec /3600) * 30 + pi
                        
              moveHands(secondAngle, minuteAngle, hourAngle)
            }
                        
            function moveHands(secondAngle, minuteAngle, hourAngle) {
                        
              const secondHand = document.getElementById("secondHand")
              const minuteHand = document.getElementById("minuteHand")
              const hourHand = document.getElementById("hourHand")
                        
              secondHand.setAttribute("transform","rotate("+ secondAngle + ")")
              minuteHand.setAttribute("transform","rotate("+ minuteAngle +")")
              hourHand.setAttribute("transform","rotate("+ hourAngle + ")")
                        
            }
            ]]>
                        
            </script>
              <defs
                 id="defs4173">
                <linearGradient
                   id="linearGradient4736-4">
                  <stop
                     id="stop4738-3"
                     offset="0"
                     style="stop-color:#818181;stop-opacity:1;" />
                  <stop
                     id="stop4740-3"
                     offset="1"
                     style="stop-color:#3d3d3d;stop-opacity:0;" />
                </linearGradient>
                <linearGradient
                   id="linearGradient4540-1">
                  <stop
                     id="stop4542-93"
                     offset="0"
                     style="stop-color:#818181;stop-opacity:0.74336284;" />
                  <stop
                     id="stop4544-6"
                     offset="1"
                     style="stop-color:#000000;stop-opacity:0;" />
                </linearGradient>
                <linearGradient
                   id="linearGradient4736-7">
                  <stop
                     id="stop4738-5"
                     offset="0"
                     style="stop-color:#818181;stop-opacity:1;" />
                  <stop
                     id="stop4740-2"
                     offset="1"
                     style="stop-color:#3d3d3d;stop-opacity:0;" />
                </linearGradient>
                <linearGradient
                   id="linearGradient4540-9">
                  <stop
                     id="stop4542-5"
                     offset="0"
                     style="stop-color:#818181;stop-opacity:0.74336284;" />
                  <stop
                     id="stop4544-45"
                     offset="1"
                     style="stop-color:#000000;stop-opacity:0;" />
                </linearGradient>
                <filter
                   id="filter4812"
                   inkscape:collect="always">
                  <feGaussianBlur
                     id="feGaussianBlur4814"
                     stdDeviation="5.9200005"
                     inkscape:collect="always" />
                </filter>
                <filter
                   id="filter4772"
                   inkscape:collect="always">
                  <feGaussianBlur
                     id="feGaussianBlur4774"
                     stdDeviation="1.0571429"
                     inkscape:collect="always" />
                </filter>
                <filter
                   id="filter4666"
                   inkscape:collect="always">
                  <feGaussianBlur
                     id="feGaussianBlur4668"
                     stdDeviation="0.52899525"
                     inkscape:collect="always" />
                </filter>
                <radialGradient
                   inkscape:collect="always"
                   xlink:href="#linearGradient4540-0"
                   id="radialGradient4617"
                   gradientUnits="userSpaceOnUse"
                   gradientTransform="matrix(1.2461165,0,0,1.2460881,3645.7457,108.03222)"
                   cx="394.53696"
                   cy="540.49286"
                   fx="394.53696"
                   fy="540.49286"
                   r="225.2818" />
                <filter
                   id="filter4593-8"
                   inkscape:collect="always"
                   color-interpolation-filters="sRGB">
                  <feGaussianBlur
                     id="feGaussianBlur4595-8"
                     stdDeviation="1.0571429"
                     inkscape:collect="always" />
                </filter>
                <linearGradient
                   id="linearGradient4540-0">
                  <stop
                     id="stop4542-9"
                     offset="0"
                     style="stop-color:#a0a0a0;stop-opacity:0.74336284;" />
                  <stop
                     id="stop4544-4"
                     offset="1"
                     style="stop-color:#000000;stop-opacity:0;" />
                </linearGradient>
                <radialGradient
                   gradientUnits="userSpaceOnUse"
                   gradientTransform="matrix(1.2461165,0,0,1.2460881,-95.633885,-102.18054)"
                   r="225.2818"
                   fy="540.49286"
                   fx="394.53696"
                   cy="540.49286"
                   cx="394.53696"
                   id="radialGradient4546-4"
                   xlink:href="#linearGradient4540-0"
                   inkscape:collect="always" />
                <filter
                   id="filter4593"
                   inkscape:collect="always">
                  <feGaussianBlur
                     id="feGaussianBlur4595"
                     stdDeviation="1.0571429"
                     inkscape:collect="always" />
                </filter>
                <linearGradient
                   id="linearGradient4540">
                  <stop
                     id="stop4542"
                     offset="0"
                     style="stop-color:#818181;stop-opacity:0.74336284;" />
                  <stop
                     id="stop4544"
                     offset="1"
                     style="stop-color:#000000;stop-opacity:0;" />
                </linearGradient>
                <linearGradient
                   id="linearGradient4704"
                   inkscape:collect="always">
                  <stop
                     id="stop4706"
                     offset="0"
                     style="stop-color:#b3b3b3;stop-opacity:1;" />
                  <stop
                     id="stop4708"
                     offset="1"
                     style="stop-color:#b3b3b3;stop-opacity:1" />
                </linearGradient>
                <linearGradient
                   id="linearGradient4736">
                  <stop
                     id="stop4738"
                     offset="0"
                     style="stop-color:#818181;stop-opacity:1;" />
                  <stop
                     id="stop4740"
                     offset="1"
                     style="stop-color:#3d3d3d;stop-opacity:0;" />
                </linearGradient>
                <linearGradient
                   id="linearGradient4782"
                   inkscape:collect="always">
                  <stop
                     id="stop4784"
                     offset="0"
                     style="stop-color:#ffffff;stop-opacity:1;" />
                  <stop
                     id="stop4786"
                     offset="1"
                     style="stop-color:#ffffff;stop-opacity:0;" />
                </linearGradient>
                <linearGradient
                   id="linearGradient4828"
                   inkscape:collect="always">
                  <stop
                     id="stop4830"
                     offset="0"
                     style="stop-color:#666666;stop-opacity:1;" />
                  <stop
                     id="stop4832"
                     offset="1"
                     style="stop-color:#666666;stop-opacity:0;" />
                </linearGradient>
                <radialGradient
                   inkscape:collect="always"
                   xlink:href="#linearGradient4540"
                   id="radialGradient4687"
                   gradientUnits="userSpaceOnUse"
                   gradientTransform="matrix(1.2461165,0,0,1.2460881,-95.461513,-104.86322)"
                   cx="394.53696"
                   cy="540.49286"
                   fx="394.53696"
                   fy="540.49286"
                   r="225.2818" />
                <linearGradient
                   inkscape:collect="always"
                   xlink:href="#linearGradient4704"
                   id="linearGradient4789"
                   gradientUnits="userSpaceOnUse"
                   x1="393.96838"
                   y1="114.73032"
                   x2="374.51849"
                   y2="740.74786" />
                <radialGradient
                   inkscape:collect="always"
                   xlink:href="#linearGradient4736"
                   id="radialGradient4791"
                   gradientUnits="userSpaceOnUse"
                   gradientTransform="matrix(0.94219392,-0.42242904,0.40910932,0.91248538,-147.40834,200.48162)"
                   cx="388.57144"
                   cy="415.21933"
                   fx="388.57144"
                   fy="415.21933"
                   r="218.00569" />
                <linearGradient
                   inkscape:collect="always"
                   xlink:href="#linearGradient4828"
                   id="linearGradient4793"
                   gradientUnits="userSpaceOnUse"
                   x1="389.64761"
                   y1="664.35223"
                   x2="593.97504"
                   y2="323.12738" />
                <linearGradient
                   inkscape:collect="always"
                   xlink:href="#linearGradient4782"
                   id="linearGradient4895"
                   gradientUnits="userSpaceOnUse"
                   gradientTransform="matrix(0.97715653,-0.2214378,0.20396295,0.97715653,-34.158429,147.38498)"
                   x1="629.27698"
                   y1="-16.794603"
                   x2="590.77997"
                   y2="245.11485" />
              </defs>
              <sodipodi:namedview
                 id="base"
                 pagecolor="#ffffff"
                 bordercolor="#666666"
                 borderopacity="1.0"
                 inkscape:pageopacity="0.0"
                 inkscape:pageshadow="2"
                 inkscape:zoom="3.959798"
                 inkscape:cx="65.889323"
                 inkscape:cy="956.02447"
                 inkscape:document-units="px"
                 inkscape:current-layer="layer1"
                 showgrid="false"
                 inkscape:window-width="1299"
                 inkscape:window-height="1057"
                 inkscape:window-x="75"
                 inkscape:window-y="75"
                 inkscape:window-maximized="0" />
              <metadata
                 id="metadata4176">
                <rdf:RDF>
                  <cc:Work
                     rdf:about="">
                    <dc:format>image/svg+xml</dc:format>
                    <dc:type
                       rdf:resource="http://purl.org/dc/dcmitype/StillImage" />
                    <dc:title></dc:title>
                  </cc:Work>
                </rdf:RDF>
              </metadata>
              <g
                 inkscape:label="Layer 1"
                 inkscape:groupmode="layer"
                 id="layer1">
                <g
                   id="mainClock"
                   transform="matrix(0.35949728,0,0,0.35949728,-18.281766,-87.769171)"
                   inkscape:label="#g4901">
                  <desc
                     id="desc5755">Main background clock</desc>
                  <path
                     transform="matrix(1.0387024,0,0,1.0379126,-55.03866,89.972241)"
                     sodipodi:type="arc"
                     style="fill:#333333;fill-opacity:1;stroke:none;filter:url(#filter4812)"
                     id="path4790"
                     sodipodi:cx="388.57144"
                     sodipodi:cy="415.21933"
                     sodipodi:rx="211.42857"
                     sodipodi:ry="211.42857"
                     d="m 600.00002,415.21933 c 0,116.76878 -94.6598,211.42857 -211.42858,211.42857 -116.76878,0 -211.42857,-94.65979 -211.42857,-211.42857 0,-116.76878 94.65979,-211.42857 211.42857,-211.42857 116.76878,0 211.42858,94.65979 211.42858,211.42857 z" />
                  <path
                     transform="translate(-39.833032,105.71425)"
                     d="m 600.00002,415.21933 c 0,116.76878 -94.6598,211.42857 -211.42858,211.42857 -116.76878,0 -211.42857,-94.65979 -211.42857,-211.42857 0,-116.76878 94.65979,-211.42857 211.42857,-211.42857 116.76878,0 211.42858,94.65979 211.42858,211.42857 z"
                     sodipodi:ry="211.42857"
                     sodipodi:rx="211.42857"
                     sodipodi:cy="415.21933"
                     sodipodi:cx="388.57144"
                     id="path3755"
                     style="fill:#333333;fill-opacity:1;stroke:url(#linearGradient4789);stroke-width:18;stroke-linejoin:bevel;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     sodipodi:type="arc" />
                  <path
                     transform="matrix(0.97450047,0,0,0.97450047,-29.924639,116.30215)"
                     sodipodi:type="arc"
                     style="fill:url(#radialGradient4791);fill-opacity:1;stroke:url(#linearGradient4793);stroke-width:8.20933437;stroke-linejoin:bevel;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none;filter:url(#filter4772)"
                     id="path4702"
                     sodipodi:cx="388.57144"
                     sodipodi:cy="415.21933"
                     sodipodi:rx="211.42857"
                     sodipodi:ry="211.42857"
                     d="m 600.00002,415.21933 c 0,116.76878 -94.6598,211.42857 -211.42858,211.42857 -116.76878,0 -211.42857,-94.65979 -211.42857,-211.42857 0,-116.76878 94.65979,-211.42857 211.42857,-211.42857 116.76878,0 211.42858,94.65979 211.42858,211.42857 z" />
                  <path
                     inkscape:connector-curvature="0"
                     id="path3759"
                     d="m 348.73841,338.73313 0,24.90052"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 348.73841,338.73313 0,24.90052"
                     id="path3947"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 439.83834,363.13958 -12.45026,21.56448"
                     id="path3953"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 506.49656,429.80056 -21.56448,12.45026"
                     id="path3959"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 530.90216,520.89825 -24.90052,0"
                     id="path3965"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="M 506.49571,611.99819 484.93123,599.54793"
                     id="path3971"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="M 439.80455,678.68796 427.35429,657.12348"
                     id="path3977"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 348.70685,703.09356 0,-24.90052"
                     id="path3983"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 257.60692,678.68711 12.45026,-21.56448"
                     id="path3989"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 190.91715,611.99595 21.56448,-12.45026"
                     id="path3995"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 166.51154,520.89826 24.90052,0"
                     id="path4001"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 190.918,429.79832 21.56448,12.45026"
                     id="path4007"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 257.60915,363.10855 12.45026,21.56448"
                     id="path4013"
                     inkscape:connector-curvature="0" />
                  <path
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 367.7835,339.72868 -1.16455,11.07994"
                     id="path4141"
                     inkscape:connector-curvature="0"
                     sodipodi:nodetypes="cc" />
                  <path
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 386.61985,342.71009 -2.31634,10.89752"
                     id="path4147"
                     inkscape:connector-curvature="0"
                     sodipodi:nodetypes="cc" />
                  <path
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 405.0413,347.64525 -3.44275,10.5957"
                     id="path4153"
                     inkscape:connector-curvature="0"
                     sodipodi:nodetypes="cc" />
                  <path
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none"
                     d="m 422.84573,354.48011 -4.53145,10.17779"
                     id="path4159"
                     inkscape:connector-curvature="0"
                     sodipodi:nodetypes="cc" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4239"
                     d="m 367.7835,339.72868 -1.16455,11.07994"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4241"
                     d="m 386.61985,342.71009 -2.31634,10.89752"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4243"
                     d="m 405.0413,347.64525 -3.44275,10.5957"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4245"
                     d="m 422.84573,354.48011 -4.53145,10.17779"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4251"
                     d="m 455.83413,373.52426 -6.5485,9.01323"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4253"
                     d="m 470.65619,385.52441 -7.45477,8.27936"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4255"
                     d="m 484.14205,399.00911 -8.27936,7.45477"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4257"
                     d="m 496.14371,413.83048 -9.01325,6.5485"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4263"
                     d="m 515.18849,446.82204 -10.17778,4.53144"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4265"
                     d="m 522.02469,464.62551 -10.5957,3.44275"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4267"
                     d="m 526.96144,483.04653 -10.89752,2.31634"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4269"
                     d="m 529.9445,501.88305 -11.07995,1.16454"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4275"
                     d="m 529.93817,539.97348 -11.07994,-1.16455"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4277"
                     d="m 526.95676,558.80983 -10.89752,-2.31634"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4279"
                     d="m 522.0216,577.23128 -10.5957,-3.44275"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4281"
                     d="m 515.18674,595.03571 -10.17779,-4.53145"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4287"
                     d="m 496.14256,628.02411 -9.01323,-6.5485"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4289"
                     d="m 484.14241,642.84617 -8.27936,-7.45477"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4291"
                     d="m 470.65771,656.33203 -7.45477,-8.27936"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4293"
                     d="m 455.83634,668.33369 -6.5485,-9.01325"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4299"
                     d="m 422.84478,687.37857 -4.53144,-10.17778"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4301"
                     d="m 405.04131,694.21477 -3.44275,-10.5957"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4303"
                     d="M 386.62029,699.15152 384.30395,688.254"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4305"
                     d="m 367.78377,702.13458 -1.16454,-11.07995"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4311"
                     d="m 329.69334,702.12819 1.16455,-11.07994"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4313"
                     d="m 310.85699,699.14678 2.31634,-10.89752"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4315"
                     d="m 292.43554,694.21162 3.44275,-10.5957"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4317"
                     d="m 274.63111,687.37676 4.53145,-10.17779"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4323"
                     d="m 241.64271,668.33264 6.5485,-9.01323"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4325"
                     d="m 226.82065,656.33249 7.45477,-8.27936"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4327"
                     d="m 213.33479,642.84779 8.27936,-7.45477"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4329"
                     d="m 201.33313,628.02642 9.01325,-6.5485"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4335"
                     d="m 182.28835,595.03486 10.17778,-4.53144"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4337"
                     d="m 175.45215,577.23139 10.5957,-3.44275"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4339"
                     d="m 170.5154,558.81037 10.89752,-2.31634"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4341"
                     d="m 167.53234,539.97385 11.07995,-1.16454"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4347"
                     d="m 167.53865,501.88332 11.07994,1.16455"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4349"
                     d="m 170.52006,483.04697 10.89752,2.31634"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4351"
                     d="m 175.45522,464.62552 10.5957,3.44275"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4353"
                     d="m 182.29008,446.82109 10.17779,4.53145"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4359"
                     d="m 201.33428,413.83279 9.01323,6.5485"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4361"
                     d="m 213.33443,399.01073 8.27936,7.45477"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4363"
                     d="m 226.81913,385.52487 7.45477,8.27936"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4365"
                     d="m 241.6405,373.52321 6.5485,9.01325"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4371"
                     d="m 274.63206,354.47833 4.53144,10.17778"
                     style="fill:#ffffff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4373"
                     d="m 292.43553,347.64213 3.44275,10.5957"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4375"
                     d="m 310.85655,342.70538 2.31634,10.89752"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <path
                     sodipodi:nodetypes="cc"
                     inkscape:connector-curvature="0"
                     id="path4377"
                     d="m 329.69307,339.72232 1.16454,11.07995"
                     style="fill:#ff00ff;fill-opacity:1;stroke:#ffffff;stroke-width:10;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none" />
                  <flowRoot
                     transform="translate(313.08216,26.133452)"
                     style="font-size:48px;font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;text-align:center;line-height:125%;letter-spacing:0px;word-spacing:0px;text-anchor:middle;fill:#000000;fill-opacity:1;stroke:none;font-family:Gentium Book Basic;-inkscape-font-specification:Gentium Book Basic"
                     id="flowRoot4444"
                     xml:space="preserve"><flowRegion
                       id="flowRegion4446"><rect
                         style="font-size:48px;font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-family:Gentium Book Basic;-inkscape-font-specification:Gentium Book Basic"
                         y="333.19702"
                         x="-2.4461401"
                         height="91.730255"
                         width="75.830338"
                         id="rect4448" /></flowRegion><flowPara
                       id="flowPara4450">12</flowPara></flowRoot>      <text
                     sodipodi:linespacing="125%"
                     id="text4883"
                     y="532.84747"
                     x="214.80678"
                     style="font-size:48px;font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;text-align:center;line-height:125%;letter-spacing:0px;word-spacing:0px;text-anchor:middle;fill:#cccccc;fill-opacity:1;stroke:none;font-family:Gentium Book Basic;-inkscape-font-specification:Gentium Book Basic"
                     xml:space="preserve"><tspan
                       y="532.84747"
                       x="214.80678"
                       id="tspan4885"
                       sodipodi:role="line">9</tspan></text>
                  <text
                     sodipodi:linespacing="125%"
                     id="text4887"
                     y="535.6853"
                     x="479.66977"
                     style="font-size:48px;font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;text-align:center;line-height:125%;letter-spacing:0px;word-spacing:0px;text-anchor:middle;fill:#cccccc;fill-opacity:1;stroke:none;font-family:Gentium Book Basic;-inkscape-font-specification:Gentium Book Basic"
                     xml:space="preserve"><tspan
                       y="535.6853"
                       x="479.66977"
                       id="tspan4889"
                       sodipodi:role="line">3</tspan></text>
                  <text
                     sodipodi:linespacing="125%"
                     id="text4891"
                     y="660.90558"
                     x="348.3197"
                     style="font-size:48px;font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;text-align:center;line-height:125%;letter-spacing:0px;word-spacing:0px;text-anchor:middle;fill:#cccccc;fill-opacity:1;stroke:none;font-family:Gentium Book Basic;-inkscape-font-specification:Gentium Book Basic"
                     xml:space="preserve"><tspan
                       y="660.90558"
                       x="348.3197"
                       id="tspan4893"
                       sodipodi:role="line">6</tspan></text>
                </g>
                        
                <g id="hands" transform="translate(108,100)">
                    <g id="minuteHand">
                        <line stroke-width="3.59497285" y2="50" stroke-linecap="round" stroke="#00fff6" opacity=".9" />
            <!--            <animateTransform attributeName="transform" type="rotate" repeatCount="indefinite" dur="60min" by="360" />-->
                    </g>
                        
                    <g id="hourHand">
                        <line stroke-width="5" y2="30" stroke-linecap="round" stroke="#ffcb00" opacity=".9" />
            <!--            <animateTransform attributeName="transform" type="rotate" repeatCount="indefinite" dur="12h" by="360" />-->
                    </g>
                    <g id="secondHand">  
                        <line stroke-width="2" y1="-20" y2="70" stroke-linecap="round" stroke="red"/>
            <!--            <animateTransform attributeName="transform" type="rotate" repeatCount="indefinite" dur="60s" by="360" />-->
                    </g>
                </g>
                <g
                   id="g5590"
                   transform="matrix(0.35949728,0,0,0.35949728,-32.601633,-49.765191)">
                  <path
                     d="m 600.00002,415.21933 c 0,116.76878 -94.6598,211.42857 -211.42858,211.42857 -116.76878,0 -211.42857,-94.65979 -211.42857,-211.42857 0,-116.76878 94.65979,-211.42857 211.42857,-211.42857 116.76878,0 211.42858,94.65979 211.42858,211.42857 z"
                     sodipodi:ry="211.42857"
                     sodipodi:rx="211.42857"
                     sodipodi:cy="415.21933"
                     sodipodi:cx="388.57144"
                     id="path4646"
                     style="fill:#000000;fill-opacity:1;stroke:#000000;stroke-width:17.19096184;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none;filter:url(#filter4593-8)"
                     sodipodi:type="arc"
                     transform="matrix(0.1163402,0,0,0.1163402,343.36495,366.90823)" />
                  <path
                     transform="matrix(0.1163402,0,0,0.1163402,343.3449,367.22032)"
                     sodipodi:type="arc"
                     style="fill:url(#radialGradient4687);fill-opacity:1;stroke:#000000;stroke-width:17.19096184;stroke-miterlimit:4;stroke-opacity:1;stroke-dasharray:none;filter:url(#filter4593)"
                     id="path3757"
                     sodipodi:cx="388.57144"
                     sodipodi:cy="415.21933"
                     sodipodi:rx="211.42857"
                     sodipodi:ry="211.42857"
                     d="m 600.00002,415.21933 c 0,116.76878 -94.6598,211.42857 -211.42858,211.42857 -116.76878,0 -211.42857,-94.65979 -211.42857,-211.42857 0,-116.76878 94.65979,-211.42857 211.42857,-211.42857 116.76878,0 211.42858,94.65979 211.42858,211.42857 z" />
                  <path
                     inkscape:connector-curvature="0"
                     id="path4548"
                     d="m 388.53812,391.6217 c -12.36089,0 -22.53642,9.7842 -24.03595,22.43429 3.34941,-10.66848 12.8297,-18.36808 24.03595,-18.36808 11.20626,0 20.71306,7.6996 24.06246,18.36808 -1.49952,-12.65009 -11.70156,-22.43429 -24.06246,-22.43429 z"
                     style="opacity:0.6;fill:#ffffff;fill-opacity:0.29585799;stroke:none;filter:url(#filter4666)" />
                </g>
                <path
                   sodipodi:type="arc"
                   style="opacity:0.9;fill:url(#linearGradient4895);fill-opacity:1;stroke:none"
                   id="Shine"
                   sodipodi:cx="603.85614"
                   sodipodi:cy="214.7122"
                   sodipodi:rx="156.30789"
                   sodipodi:ry="96.189468"
                   d="m 760.16403,214.7122 a 156.30789,96.189468 0 1 1 -312.61578,0 156.30789,96.189468 0 1 1 312.61578,0 z"
                   transform="matrix(0.18252314,0.1381499,-0.13258681,0.17517321,53.771369,-58.137411)"
                   inkscape:label="#path4780">
                  <desc
                     id="desc4981">Shiney Part</desc>
                </path>
                <text
                   xml:space="preserve"
                   style="font-size:17.25586891px;font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;text-align:center;line-height:125%;letter-spacing:0px;word-spacing:0px;text-anchor:middle;fill:#cccccc;fill-opacity:1;stroke:none;font-family:Gentium Book Basic;-inkscape-font-specification:Gentium Book Basic"
                   x="107.51204"
                   y="55.747242"
                   id="text4897"
                   sodipodi:linespacing="125%"><tspan
                     sodipodi:role="line"
                     id="tspan4899"
                     x="107.51204"
                     y="55.747242">12</tspan></text>
              </g>
            </svg>
            """;
}
