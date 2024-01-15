// Hierarchical Clustering builds a tree of clusters.
// It can be agglomerative (bottom-up) or divisive (top-down).
// In the agglomerative approach, each data point starts as a separate cluster, and pairs of clusters are successively merged based on their similarity.
// In the divisive approach, all data points begin in one cluster, and the algorithm recursively splits clusters.

// Imagine you have a dataset of customer purchase history, and you want to group customers based on their purchasing behavior.
// Hierarchical clustering can help identify different levels of similarity among customers, forming a hierarchical structure of clusters.

// The following dependency is to be added to the build.gradle file:
// implementation 'com.github.haifengl:smile-core:2.5.0'

import smile.clustering.linkage.CompleteLinkage
import smile.clustering.linkage.Linkage
import smile.clustering.linkage.WardLinkage
import smile.data.Attribute
import smile.data.AttributeDataset
import smile.data.NominalAttribute
import smile.data.parser.DelimitedTextParser
import java.io.File