class MyAccessibilityService : AccessibilityService() {
    
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    
    override fun onInterrupt() {}
    
    companion object {
        private var instance: MyAccessibilityService? = null
        
        fun performClick(rect: Rect) {
            instance?.let {
                val gestureBuilder = GestureDescription.Builder()
                val path = Path()
                path.moveTo(rect.centerX().toFloat(), rect.centerY().toFloat())
                
                val gesture = gestureBuilder
                    .addStroke(GestureDescription.StrokeDescription(path, 0, 100))
                    .build()
                
                it.dispatchGesture(gesture, null, null)
            }
        }
    }
    
    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }
} 