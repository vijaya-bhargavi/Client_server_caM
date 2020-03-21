import sys
import cv2
import matplotlib.pyplot as plt

S=sys.argv
img=cv2.imread(S[1],-1)
cv2.imshow("Recombined Image",img)

cv2.waitKey(0)
cv2.destroyAllWindows()
