public class LiskovSubstitution {

    // Wrong use:

    static class Rectangle {
        protected int w;
        protected int h;

        void setW(int w) { this.w = w; }

        void setH(int h) { this.h = h; }

        int getW() { return w; }

        int getH() { return h; }

        int getArea() { return w * h; }
    }

    static class Square extends Rectangle {
        @Override
        void setW(int w) {
            this.w = w;
            this.h = w; // Principle violation
        }

        @Override
        void setH(int h) {
            this.w = h;
            this.h = h; // Principle violation
        }

        int getW() { return super.getW(); }

        int getH() { return super.getH(); }

        int getArea() { return super.getArea(); }
    }

    // Correct use:

    abstract static class Shape {
        public abstract int getArea();
    }

    static class CorrectRectangle extends Shape{
        private int w;
        private int h;

        void setW(int w) { this.w = w; }

        void setH(int h) { this.h = h; }

        int getW() { return w; }

        int getH() { return h; }

        @Override
        public int getArea() {
            return w * h;
        }
    }

    static class CorrectSquare extends Shape{
        private int side;

        int getSide() { return this.side; }

        void setSide(int side) { this.side = side; }

        @Override
        public int getArea() {
            return side * side;
        }
    }

    public static void main(String[] args) {}

}

/*
Anteriormente, a classe Square implementava a classe Rectangle, partindo de uma premissa que todo quadrado é um retângulo. Porém
quando chamamos os métodos para definir as dimensões do quadrado, precisamos sobrescrever o método original, sinalizando que
a nossa suposta premissa apresenta erros. Com a correção, agora tanto Square quanto Rectangle depende de uma classe abstrata
chamada Shape (equivalente a forma geométrica), e dessa vez, não possuimos sobrescrita de nenhum método, pois cada implementação
é indendepente
 */
