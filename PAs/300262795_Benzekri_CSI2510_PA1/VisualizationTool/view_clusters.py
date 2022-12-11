import pandas as pd
import open3d as o3d
import os
import numpy as np

#Get raw point cloud:
df_Raw_Point_Cloud = pd.read_csv("Point_Cloud_1_clusters.csv")

#Get raw point cloud:
df_Raw_Point_Cloud = pd.read_csv("Point_Cloud_1_clusters.csv")

#Full point cloud:
pcd = o3d.geometry.PointCloud()
points = np.vstack((df_Raw_Point_Cloud.x, df_Raw_Point_Cloud.y, df_Raw_Point_Cloud.z)).transpose()
colors = np.vstack((df_Raw_Point_Cloud.R, df_Raw_Point_Cloud.G, df_Raw_Point_Cloud.B)).transpose()
pcd.colors = o3d.utility.Vector3dVector(colors)
pcd.points = o3d.utility.Vector3dVector(points)
o3d.visualization.draw_geometries([pcd])
