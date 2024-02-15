// SVD is a matrix factorization technique that decomposes a matrix A into three matrices U, Σ, and V^T, such that:

// A=UΣV^T

// U is an m x m orthogonal matrix containing the left singular vectors.
// Σ is an m x n diagonal matrix containing the singular values in descending order.
// V^T is an n×n orthogonal matrix containing the right singular vectors.

// Imagine you have a large dataset representing user ratings for different movies in a movie recommendation system.
// Each row represents a user, each column represents a movie, and the entries represent the ratings.
// SVD can be used to decompose this matrix into three matrices representing latent factors such as user preferences and movie features.
// By approximating the original matrix with a lower-rank approximation obtained from SVD, you can effectively perform dimensionality reduction while preserving the essential information needed for accurate recommendations.
