The modify styles apply not to the feature's geometry but to the generated vertex points of the feature's geometry. Thus, the created style should be applicable to the `Point` geometry. The modify styles can be set:
- Globally, using the layer's modify styles.
- Individually for specific features.

Note, modify styles will be applied when the `featureModifyEnabled` attribute in the vector source is set to `true` and modify mode is activated.
