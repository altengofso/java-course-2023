package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public sealed interface ValueGenerator {
    Object generate(Annotation[] annotations);

    record ShortGenerator() implements ValueGenerator {
        @Override
        public Object generate(Annotation[] annotations) {
            short min = Short.MIN_VALUE;
            short max = Short.MAX_VALUE;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Min minValueAnnotation) {
                    min = (short) minValueAnnotation.value();
                } else if (annotation instanceof Max maxValueAnnotation) {
                    max = (short) maxValueAnnotation.value();
                }
            }
            return (short) ThreadLocalRandom.current().nextInt(min, max);
        }
    }

    record IntGenerator() implements ValueGenerator {
        @Override
        public Object generate(Annotation[] annotations) {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Min minValueAnnotation) {
                    min = (int) minValueAnnotation.value();
                } else if (annotation instanceof Max maxValueAnnotation) {
                    max = (int) maxValueAnnotation.value();
                }
            }
            return ThreadLocalRandom.current().nextInt(min, max);
        }
    }

    record LongGenerator() implements ValueGenerator {
        @Override
        public Object generate(Annotation[] annotations) {
            long min = Long.MIN_VALUE;
            long max = Long.MAX_VALUE;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Min minValueAnnotation) {
                    min = minValueAnnotation.value();
                } else if (annotation instanceof Max maxValueAnnotation) {
                    max = maxValueAnnotation.value();
                }
            }
            return ThreadLocalRandom.current().nextLong(min, max);
        }
    }

    record FLoatGenerator() implements ValueGenerator {
        @Override
        public Object generate(Annotation[] annotations) {
            float min = Float.MIN_VALUE;
            float max = Float.MAX_VALUE;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Min minValueAnnotation) {
                    min = (float) minValueAnnotation.value();
                } else if (annotation instanceof Max maxValueAnnotation) {
                    max = (float) maxValueAnnotation.value();
                }
            }
            return ThreadLocalRandom.current().nextFloat(min, max);
        }
    }

    record DoubleGenerator() implements ValueGenerator {
        @Override
        public Object generate(Annotation[] annotations) {
            double min = Double.MIN_VALUE;
            double max = Double.MAX_VALUE;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Min minValueAnnotation) {
                    min = (double) minValueAnnotation.value();
                } else if (annotation instanceof Max maxValueAnnotation) {
                    max = (double) maxValueAnnotation.value();
                }
            }
            return ThreadLocalRandom.current().nextDouble(min, max);
        }
    }

    record BooleanGenerator() implements ValueGenerator {
        @Override
        public Object generate(Annotation[] annotations) {
            return ThreadLocalRandom.current().nextBoolean();
        }
    }

    @SuppressWarnings("MagicNumber")
    record StringGenerator() implements ValueGenerator {
        @Override
        public Object generate(Annotation[] annotations) {
            int stringLength = 10;
            boolean notNull = false;
            for (Annotation annotation : annotations) {
                if (annotation instanceof NotNull) {
                    notNull = true;
                } else if (annotation instanceof Min minValueAnnotation) {
                    stringLength = (int) minValueAnnotation.value();
                }
            }
            if (!notNull) {
                return "";
            }
            int leftLimit = 97;
            int rightLimit = 122;
            return ThreadLocalRandom.current().ints(stringLength, leftLimit, rightLimit + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        }
    }
}
