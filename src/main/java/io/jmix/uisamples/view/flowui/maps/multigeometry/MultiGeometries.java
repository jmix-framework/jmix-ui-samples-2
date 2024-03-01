package io.jmix.uisamples.view.flowui.maps.multigeometry;

import io.jmix.maps.utils.GeometryUtils;
import org.locationtech.jts.geom.*;

public final class MultiGeometries {

    private static final GeometryFactory factory = GeometryUtils.getGeometryFactory();

    public static Point[] getPoints() {
        return new Point[]{
                factory.createPoint(new Coordinate(-122.33375313822853, 47.60823795943953)),
                factory.createPoint(new Coordinate(-122.45611331478547, 47.25954321598649)),
                factory.createPoint(new Coordinate(-119.29322124895481, 46.27572989137407)),
                factory.createPoint(new Coordinate(-117.40142790836755, 47.66306824198439)),
                factory.createPoint(new Coordinate(-120.52269825738927, 46.613138505298224)),
        };
    }

    public static LineString[] getLineString() {
        return new LineString[]{
                factory.createLineString(new Coordinate[]{
                        new Coordinate(-119.2602013744234, 46.25777727894834),
                        new Coordinate(-119.2602013744234, 46.25777727894834),
                        new Coordinate(-119.21882617651447, 46.27606318233853),
                        new Coordinate(-119.1270173054148, 46.248632039769575),
                        new Coordinate(-119.08221457631814, 46.25184998300088),
                        new Coordinate(-119.09283951518123, 46.3398270352491),
                        new Coordinate(-119.01028682699169, 46.48328753306612),
                        new Coordinate(-119.01296710907575, 46.53975456442208),
                        new Coordinate(-118.85215018403109, 46.648095177007036),
                        new Coordinate(-118.83499637869305, 46.72167249933594),
                        new Coordinate(-118.69883804882193, 46.81937507812978),
                        new Coordinate(-118.5508864777809, 46.99596361820818),
                        new Coordinate(-118.41580818170597, 47.05980030698376),
                        new Coordinate(-118.40508705336966, 47.10362136235713),
                        new Coordinate(-118.3686352170262, 47.11603077529699),
                        new Coordinate(-118.0984627829512, 47.249431269250096),
                        new Coordinate(-117.91191514989939, 47.33672505571039),
                        new Coordinate(-117.59438452831213, 47.56531088456211),
                        new Coordinate(-117.40998112092761, 47.65569472161292),
                }),
                factory.createLineString(new Coordinate[]{
                        new Coordinate(-122.43069960813438, 47.23270042695498),
                        new Coordinate(-122.40457839568656, 47.23956921282942),
                        new Coordinate(-122.33737947011518, 47.24200169431646),
                        new Coordinate(-122.33021720218593, 47.25788162232266),
                        new Coordinate(-122.30690806144975, 47.28995622417898),
                        new Coordinate(-122.29701753650451, 47.31622402697502),
                        new Coordinate(-122.29299948649103, 47.34596109720488),
                        new Coordinate(-122.29684015277807, 47.35768646633349),
                        new Coordinate(-122.29033126014265, 47.38645053211286),
                        new Coordinate(-122.29320129586652, 47.404837734330386),
                        new Coordinate(-122.2868872172739, 47.42077140343068),
                        new Coordinate(-122.27057940390051, 47.432437300980496),
                        new Coordinate(-122.26346171530514, 47.46094409452522),
                        new Coordinate(-122.27224163877352, 47.481703834602314),
                        new Coordinate(-122.26438226507412, 47.49037479841499),
                        new Coordinate(-122.28291502665822, 47.509698369273224),
                        new Coordinate(-122.29719122277501, 47.53613503561368),
                        new Coordinate(-122.31761556174504, 47.55260281166403),
                        new Coordinate(-122.32167958837678, 47.561327228434806),
                        new Coordinate(-122.32095574898841, 47.59390005876091),
                        new Coordinate(-122.3313375987638, 47.60874888971671),
                })
        };
    }

    public static Polygon[] getPolygons() {
        return new Polygon[]{
                factory.createPolygon(
                        new Coordinate[]{
                                new Coordinate(-117.033359, 49.000239),
                                new Coordinate(-117.044313, 47.762451),
                                new Coordinate(-117.038836, 46.426077),
                                new Coordinate(-117.055267, 46.343923),
                                new Coordinate(-116.92382, 46.168661),
                                new Coordinate(-116.918344, 45.993399),
                                new Coordinate(-118.988627, 45.998876),
                                new Coordinate(-119.125551, 45.933153),
                                new Coordinate(-119.525367, 45.911245),
                                new Coordinate(-119.963522, 45.823614),
                                new Coordinate(-120.209985, 45.725029),
                                new Coordinate(-120.505739, 45.697644),
                                new Coordinate(-120.637186, 45.746937),
                                new Coordinate(-121.18488, 45.604536),
                                new Coordinate(-121.217742, 45.670259),
                                new Coordinate(-121.535404, 45.725029),
                                new Coordinate(-121.809251, 45.708598),
                                new Coordinate(-122.247407, 45.549767),
                                new Coordinate(-122.762239, 45.659305),
                                new Coordinate(-122.811531, 45.960537),
                                new Coordinate(-122.904639, 46.08103),
                                new Coordinate(-123.11824, 46.185092),
                                new Coordinate(-123.211348, 46.174138),
                                new Coordinate(-123.370179, 46.146753),
                                new Coordinate(-123.545441, 46.261769),
                                new Coordinate(-123.72618, 46.300108),
                                new Coordinate(-123.874058, 46.239861),
                                new Coordinate(-124.065751, 46.327492),
                                new Coordinate(-124.027412, 46.464416),
                                new Coordinate(-123.895966, 46.535616),
                                new Coordinate(-124.098612, 46.74374),
                                new Coordinate(-124.235536, 47.285957),
                                new Coordinate(-124.31769, 47.357157),
                                new Coordinate(-124.427229, 47.740543),
                                new Coordinate(-124.624399, 47.88842),
                                new Coordinate(-124.706553, 48.184175),
                                new Coordinate(-124.597014, 48.381345),
                                new Coordinate(-124.394367, 48.288237),
                                new Coordinate(-123.983597, 48.162267),
                                new Coordinate(-123.704273, 48.167744),
                                new Coordinate(-123.424949, 48.118452),
                                new Coordinate(-123.162056, 48.167744),
                                new Coordinate(-123.036086, 48.080113),
                                new Coordinate(-122.800578, 48.08559),
                                new Coordinate(-122.636269, 47.866512),
                                new Coordinate(-122.515777, 47.882943),
                                new Coordinate(-122.493869, 47.587189),
                                new Coordinate(-122.422669, 47.318818),
                                new Coordinate(-122.324084, 47.346203),
                                new Coordinate(-122.422669, 47.576235),
                                new Coordinate(-122.395284, 47.800789),
                                new Coordinate(-122.230976, 48.030821),
                                new Coordinate(-122.362422, 48.123929),
                                new Coordinate(-122.373376, 48.288237),
                                new Coordinate(-122.471961, 48.468976),
                                new Coordinate(-122.422669, 48.600422),
                                new Coordinate(-122.488392, 48.753777),
                                new Coordinate(-122.647223, 48.775685),
                                new Coordinate(-122.795101, 48.8907),
                                new Coordinate(-122.756762, 49.000239),
                                new Coordinate(-117.033359, 49.000239),
                        }), factory.createPolygon(
                new Coordinate[]{
                        new Coordinate(-122.718423, 48.310145),
                        new Coordinate(-122.586977, 48.35396),
                        new Coordinate(-122.608885, 48.151313),
                        new Coordinate(-122.767716, 48.227991),
                        new Coordinate(-122.718423, 48.310145),
                }), factory.createPolygon(
                new Coordinate[]{
                        new Coordinate(-123.025132, 48.583992),
                        new Coordinate(-122.915593, 48.715438),
                        new Coordinate(-122.767716, 48.556607),
                        new Coordinate(-122.811531, 48.419683),
                        new Coordinate(-123.041563, 48.458022),
                        new Coordinate(-123.025132, 48.583992),
                }),
        };
    }
}
