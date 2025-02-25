The `DataVectorSource` provides the ability to set a modify style generator to generate styles for each geo-object on the source.

The modify styles apply not to the feature's geometry but to the generated vertex points of the feature's geometry. Thus, the created style should be applicable to the `Point` geometry.

Note, select styles will be applied when the `featureSelectEnabled` attribute in the vector source is set to `true`.
